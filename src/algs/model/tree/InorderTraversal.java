package algs.model.tree;

import algs.model.IBinaryTreeNode;

/**
 * Perform an inorder traversal of the tree.
 * 
 * Left - Self - Right
 * 
 * @param <T>
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public class InorderTraversal<T extends IBinaryTreeNode> extends AbstractBinaryTraversal {

	/** Start at the given node. */
	@SuppressWarnings("unchecked")
	public InorderTraversal(T node) {
		super(node);
	}

	/**
	 * Initial phase for inorder traversal is LEFT.
	 *
	 * @see AbstractBinaryTraversal#initialPhase()
	 */
	@Override
	public Phase initialPhase() {
		return Phase.LEFT;
	}
	
	/**
	 * Final phase for inorder traversal is RIGHT.
	 * 
	 * @see AbstractBinaryTraversal#finalPhase()
	 */
	@Override
	public Phase finalPhase() {
		return Phase.RIGHT;
	}

	/**
	 * Advance phase to follow inorder traversal.
	 * 
	 * @see AbstractBinaryTraversal#advancePhase(algs.model.tree.AbstractBinaryTraversal.Phase)
	 */
	@Override
	public Phase advancePhase(Phase phase) {
		switch (phase) {
			case LEFT: return Phase.SELF;
			case RIGHT: return Phase.DONE;
		}
		
		// must be self.
		return Phase.RIGHT;
	}

}
