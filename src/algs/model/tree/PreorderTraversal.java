package algs.model.tree;

import algs.model.IBinaryTreeNode;

/**
 * Perform a pre traversal of the tree.
 * 
 * Self - Left - Right
 * 
 * @param <T>
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public class PreorderTraversal<T extends IBinaryTreeNode> extends AbstractBinaryTraversal {

	/** Start at the given node. */
	public PreorderTraversal(T node) {
		super(node);
	}

	/**
	 * Initial phase for preorder traversal is SELF.
	 *
	 * @see AbstractBinaryTraversal#initialPhase()
	 */
	@Override
	public Phase initialPhase() {
		return Phase.SELF;
	}
	
	/**
	 * Final phase for preorder traversal is RIGHT.
	 * 
	 * @see AbstractBinaryTraversal#finalPhase()
	 */	@Override
	public Phase finalPhase() {
		return Phase.RIGHT;
	}

	/**
	 * Advance phase to follow preorder traversal.
	 * 
	 * @see AbstractBinaryTraversal#advancePhase(algs.model.tree.AbstractBinaryTraversal.Phase)
	 */
	@Override
	public Phase advancePhase(Phase phase) {
		switch (phase) {
			case LEFT: return Phase.RIGHT;
			case RIGHT: return Phase.DONE;
		}
		
		// must be SELF:
		return Phase.LEFT;
	}

}
