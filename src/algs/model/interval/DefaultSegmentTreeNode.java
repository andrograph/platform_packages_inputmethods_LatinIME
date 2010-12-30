package algs.model.interval;

/**
 * Provides default implementation of a SegmentTreeNode that requires
 * no additional storage besides count.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
class DefaultSegmentTreeNode extends SegmentTreeNode {

	/**
	 * Default constructor.
	 * 
	 * @param left      left value for range 
	 * @param right     right value for range
	 */
	public DefaultSegmentTreeNode(int left, int right) {
		super(left, right);
	}
	
	/**
	 * Provide default implementation that instantiates the actual node.
	 * 
	 * @see IConstructor#construct(int, int)
	 * 
	 * @param  left     left value for range
	 * @param right     right value for range
	 */
	protected SegmentTreeNode construct(int left, int right) {
		return new DefaultSegmentTreeNode (left, right);
	}
}
