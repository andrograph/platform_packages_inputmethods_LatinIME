package algs.model.problems.segmentIntersection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import algs.model.FloatingPoint;
import algs.model.ILineSegment;
import algs.model.IPoint;
import algs.model.list.List;
import algs.model.list.Node;

/**
 * This superclass has been designed to enable the side-by-side comparison
 * of a number of line segment variations, where different data structures
 * are used to support the core algorithm.
 * <p>
 * Note that one implication of using this structure is that the line state 
 * may not be emptied in between executions.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public abstract class IntersectionDetection {

	/** 
	 * Reported intersections. 
	 *
	 * key: IPoint with the intersection
	 * value: Array of the segments that intersect at that location.
	 */
	protected Hashtable<IPoint,ILineSegment[]> report;

	/** computed time. */
	private long startingTime;
	private long computedTime;

	/**
	 * The subclass determines all intersections.
	 * 
	 * @param segments   Segments that form the input set S.
	 */
	public abstract Hashtable<IPoint,ILineSegment[]> intersections (ILineSegment[] segments);

	/**
	 * Compute the intersection of all segments when given a Collection of segments.
	 * 
	 * @param segments
	 */
	public Hashtable<IPoint,ILineSegment[]> intersections (Collection<ILineSegment> segments) {
		return intersections(segments.toArray(new ILineSegment[]{}));
	}

	/** Initialize algorithm. */
	protected void initialize () {
		report = new Hashtable<IPoint,ILineSegment[]>();
	}

	/**
	 * Add the intersection to our report of these two segments. 
	 * 
	 * Take care that the same line segment is not added twice.
	 * <p>
	 * It is clear that the performance of this method can be improved upon.
	 * Note how it uses the inefficient pattern to only increase the size
	 * of the array by two with each discovered point. However, since this
	 * implementation affects equally the brute force implementation as well
	 * as the LineSweep implementation, it was left as is.
	 * <p>
	 * TODO: A proper rewrite would be to use an improved data structure for
	 * storing the intersection pairs to avoid duplicates.
	 * 
	 * @param p   an intersection point
	 * @param il1 a member of a line pair that intersects at that intersection point
	 * @param il2 the other member of a line pair that also intersects at that intersection point 
	 */
	protected void record(IPoint p, ILineSegment il1, ILineSegment il2) {
		ILineSegment[] segs = report.get(p);

		if (segs != null) {
			// assume we can add both of them...
			int add1 = 1, add2 = 1;

			for (int i = 0; i < segs.length; i++) {
				if (add1 == 1 && segs[i] == il1) { add1 = 0; }
				if (add2 == 1 && segs[i] == il2) { add2 = 0; }
			}
			
			// both already in there! No need to add.
			if (add1+add2 == 0) { return; }
			
			// simply add to this array. We first pre-insert the old ones.
			int idx = segs.length;
			ILineSegment [] newArray = new ILineSegment[segs.length+add1+add2];
			System.arraycopy(segs, 0, newArray, 0, segs.length);
			if (add1 == 1) { newArray[idx++] = il1; }
			if (add2 == 1) { newArray[idx++] = il2; }
			
			segs = newArray;
		}  else {
			segs = new ILineSegment[2];
			segs[0] = il1;
			segs[1] = il2;
		}
		
		// add to report.
		report.put(p, segs);
	}

	/**
	 * Add the intersection to our report.
	 * <p>
	 * Make sure that all reported intersections are merged with existing
	 * ones in the report, if this method is invoked multiple times with 
	 * the same intersection point p.
	 * 
	 * @param p
	 * @param lists
	 */
	protected void record(IPoint p, List<ILineSegment>[] lists) {

		int sz = 0;
		for (List<ILineSegment> al : lists) {
			sz += al.size();
		}

		ILineSegment[] segs;
		segs = report.get(p);

		int idx = 0;
		if (segs != null) {
			// simply add to this array. We first pre-insert the old ones.
			idx = segs.length;
			ILineSegment [] newArray = new ILineSegment[segs.length+sz];
			System.arraycopy(segs, 0, newArray, 0, segs.length);
			segs = newArray;
		}  else {
			segs = new ILineSegment[sz];
			idx = 0;
		}

		for (List<ILineSegment> list : lists) {
			Node<ILineSegment> node = list.head();
			while (node != null) {
				segs[idx++] = node.value();
				node = node.next();
			}
		}

		// add to report by overwriting old one.
		report.put(p, segs);
	}

	/**
	 * Compute the intersection of all segments when given an Iterator of segments.
	 * 
	 * @param it   Iterator of line segments from which intersections are to be computed.
	 */
	public Hashtable<IPoint,ILineSegment[]> intersections (Iterator<ILineSegment> it) {
		Collection<ILineSegment> c = new ArrayList<ILineSegment>();
		while (it.hasNext()) {
			c.add(it.next());
		}

		return intersections(c.toArray(new ILineSegment[]{}));
	}

	/** Return last time to complete algorithm (in Milliseconds). */
	public long time() {
		return computedTime;
	}

	// timing

	protected void computeTime() {
		computedTime = System.currentTimeMillis()-startingTime;
	}

	protected void startTime() {
		startingTime = System.currentTimeMillis();
	}

	/** 
	 * Helper function (for debugging) to ensure that the two resulting computations
	 * are the same within an epsilon (for the intersection points).
	 * 
	 * Generates a meaningful report if all found intersections are close enough.
	 */
	public static boolean sameWithinEpsilon (
			Hashtable<IPoint,ILineSegment[]> result1,
			Hashtable<IPoint,ILineSegment[]> result2) {

		// show results.
		for (IPoint ip1 : result1.keySet()) {
			for (IPoint ip2 : result2.keySet()) {
				if (FloatingPoint.value(ip1.getX() - ip2.getX()) != 0.0) { continue; }
				if (FloatingPoint.value(ip1.getY() - ip2.getY()) != 0.0) { continue; }

				// the same. compare intersections, which may be in any order.
				ArrayList<LineSegmentPair> results = new ArrayList<LineSegmentPair>();
				ILineSegment segs1[] = result1.get(ip1);
				for (int i = 0; i < segs1.length-1; i++) {
					for (int j = i+1; j < segs1.length; j++) {
						results.add(new LineSegmentPair(segs1[i], segs1[j]));
					}
				}

				// now remove each one
				ILineSegment segs2[] = result2.get(ip2);
				for (int i = 0; i < segs2.length-1; i++) {
					for (int j = i+1; j < segs2.length; j++) {
						LineSegmentPair lsp = new LineSegmentPair(segs2[i], segs2[j]);
						if (!results.remove (lsp)) {
							// error
							System.out.println("Unexpected intersection pair:" + lsp);
							ILineSegment s1[] = result1.get(ip1);
							ILineSegment s2[] = result2.get(ip2);
							for (int k = 0; k < s1.length; k++) {
								System.out.println("  " + s1[k]);
							}
							System.out.println("------------------------------");
							for (int k = 0; k < s2.length; k++) {
								System.out.println("  " + s2[k]);
							}
							return false;
						}
					}
				}

				if (results.size() != 0) {
					System.out.println("Unmatched intersection pair.");
					return false;
				}
			}
		}

		return true;  // must be the same!
	}

	/**
	 * Convenient helper class to format the output from intersection algorithms.
	 */
	public void output (Hashtable<IPoint,ILineSegment[]> result) {
		for (IPoint ip : result.keySet()) {
			ILineSegment[] ilss = result.get(ip);
			System.out.println (ip);
			for (ILineSegment ils : ilss) {
				System.out.println ("  " + ils);
			}
			System.out.println();
		}
	}
}
