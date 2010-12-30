package algs.model.interval;

/**
 * Interface for constructing nodes in a Segment Tree.
 * <p>
 * Exposed in this way to enable individual SegmentTrees to have nodes that
 * store different pieces of information, yet need to construct nodes as needed.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public interface IConstructor {
	
	/**
	 * Instantiate the actual node.
	 * 
	 * @param left    left boundary of the range
	 * @param right   right boundary of the range
	 */
	SegmentTreeNode construct(int left, int right);
}
