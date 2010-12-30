package algs.model.searchtree;

/**
 * A valid move in the Search Tree.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public interface IMove {

	/** 
	 * Execute the move on the board state.
	 * 
	 * @param state
	 */
	boolean execute (INode state);
	
	/** 
	 * Undo the move on the board state.
	 * 
	 * @param state
	 */
	boolean undo (INode state);
	
	/** 
	 * Determine if move is valid in the board state. 
	 * 
	 * @param state
	 */
	boolean isValid (INode state);
	
}
