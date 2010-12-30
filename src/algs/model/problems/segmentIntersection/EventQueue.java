package algs.model.problems.segmentIntersection;

import algs.model.tree.BalancedBinaryNode;
import algs.model.tree.BalancedTree;

/**
 * The EventQueue for a horizontal-sweep line algorithm for line segment intersection.
 * <p>
 * The EventQueue must not be a heap-based implementation, since we will be called upon 
 * to test if an EventPoint is already within the Queue.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class EventQueue {

	/** 
	 * Use balanced binary tree to store points. Values are same
	 * as keys to enable insert to retrieve old keys as values.
	 */
	BalancedTree<EventPoint, EventPoint> events;
	
	/** Default constructor. */
	public EventQueue() {
		events = new BalancedTree<EventPoint,EventPoint>(EventPoint.eventPointSorter);
	}
	
	/** Return whether the event queue is empty. */
	public boolean isEmpty() {
		return events.size() == 0;
	}
	
	/** 
	 * Insert the Event Point into the queue, taking care to properly
	 * maintain the set of segments associated with this EventPoint if
	 * it is indeed has upper Segments.
	 */
	public void insert (EventPoint ep) {
		EventPoint oldOne = events.insert(ep, ep);
		
		if (oldOne != null) {
			// make sure upper end points are inserted.
			ep.addUpperLineSegments(oldOne.upperEndpointSegments());
		}
	}
	
	/** Remove and return left-most child [the smallest one]. */
	public EventPoint min() {
		BalancedBinaryNode<EventPoint,EventPoint> bn = events.firstNode();
		EventPoint key = bn.key();
		events.remove(key);
		
		return key;
	}

	/** 
	 * Determine whether event point already exists within the queue.
	 * 
	 * @param ep   event point to probe for
	 */
	public boolean contains(EventPoint ep) {
		BalancedBinaryNode<EventPoint,EventPoint> bn = events.getEntry(ep);
		return (bn != null);
	}
	
	/**
	 * Determine whether event point already exists within the queue, and 
	 * return the actual event point within the queue if it does.
	 *  
	 * @param ep
	 */
	public EventPoint event(EventPoint ep) {
		BalancedBinaryNode<EventPoint,EventPoint> bn = events.getEntry(ep);
		if (bn == null) { return null; } 
		return bn.key();
	}
}
