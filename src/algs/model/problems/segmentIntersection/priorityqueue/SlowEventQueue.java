package algs.model.problems.segmentIntersection.priorityqueue;

import java.util.Iterator;
import java.util.PriorityQueue;

import algs.model.problems.segmentIntersection.EventPoint;
import algs.model.problems.segmentIntersection.LineSweep;

/**
 * The EventQueue for a horizontal-sweep line algorithm for line segment intersection.
 * <p>
 * This EventQueue shows what happens when the wrong data structure is used, specifically
 * if a heap-based implementation the priority queue is used, then contains is inefficient.
 * We use the {@link PriorityQueue} class provided by the JDK to show how this can happen. 
 * 
 * To use this SlowEventQueue instead of the default one, you must actually modify
 * the {@link LineSweep} class.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class SlowEventQueue {

	/** 
	 * Use built in {@link PriorityQueue} implementation to store the events. The
	 * problem is this implementation uses a heap which makes the {@link #contains(EventPoint)}
	 * implementation an inefficient O(n).
	 */
	PriorityQueue<EventPoint> events;
	
	/** Default constructor. */
	public SlowEventQueue() {
		events = new PriorityQueue<EventPoint> (10, EventPoint.eventPointSorter);
	}
	
	/** Return whether the event queue is empty. */
	public boolean isEmpty() {
		return events.size() == 0;
	}
	
	/** 
	 * Insert the Event Point into the queue, taking care to properly
	 * maintain the set of segments associated with this EventPoint if
	 * it is indeed has upper Segments.
	 * <p>
	 * Note that we must merge the upper endpoints associated with ep
	 * with the existing event point in the queue, should one exist.
	 * 
	 * @param  ep   point to be inserted.
	 */
	public void insert (EventPoint ep) {
		Iterator<EventPoint> it = events.iterator();
		while (it.hasNext()) {
			EventPoint exist = it.next();
			if (exist.equals(ep)) {
				exist.addUpperLineSegments(ep.upperEndpointSegments());
				return;
			}
		}
		
		// if we get here, then wasn't in set to begin with!
		events.add(ep);
	}
	
	/** Remove and return left-most child [the smallest one]. */
	public EventPoint min() {
		return events.poll();
	}

	/** Determine whether event point already exists within the queue. */
	public boolean contains(EventPoint ep) {
		return events.contains(ep);
	}
	
	/** Determine whether event point already exists within the queue, and return it if it does. */
	public EventPoint event(EventPoint ep) {
		Iterator<EventPoint> it = events.iterator();
		while (it.hasNext()) {
			EventPoint exist = it.next();
			if (exist.equals(ep)) {
				exist.addUpperLineSegments(ep.upperEndpointSegments());
				return exist;
			}
		}
		
		return null;
	}
}
