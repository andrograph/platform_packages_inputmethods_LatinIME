package algs.model.tree;

import algs.model.IBinaryTreeNode;

/**
 * Perform a post traversal of the tree.
 * 
 * Left - Right - Self
 * 
 * @param <T>
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public class PostorderTraversal<T extends IBinaryTreeNode> extends AbstractBinaryTraversal {

	/** Start at the given node. */
	@SuppressWarnings("unchecked")
	public PostorderTraversal(T node) {
		super(node);
	}

	/**
	 * Initial phase for postorder traversal is LEFT.
	 *
	 * @see AbstractBinaryTraversal#initialPhase()
	 */
	@Override
	public Phase initialPhase() {
		return Phase.LEFT;
	}
	
	/**
	 * Final phase for postorder traversal is SELF.
	 * 
	 * @see AbstractBinaryTraversal#finalPhase()
	 */
	@Override
	public Phase finalPhase() {
		return Phase.SELF;
	}

	/**
	 * Advance phase to follow postorder traversal.
	 * 
	 * @see AbstractBinaryTraversal#advancePhase(algs.model.tree.AbstractBinaryTraversal.Phase)
	 */	
	@Override
	public Phase advancePhase(Phase phase) {
		switch (phase) {
			case LEFT: return Phase.RIGHT;
			case RIGHT: return Phase.SELF;
		}
		
		// must be SELF
		return Phase.DONE;
	}

}
