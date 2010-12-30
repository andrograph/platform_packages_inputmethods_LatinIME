package algs.model.gametree;

/**
 * A valid move in the GameTree.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public interface IGameMove {

	/**
	 * Execute the move on the game state. 
	 *
	 * @param   state    game tree state to be updated by this move
	 */
	boolean execute (IGameState state);
	
	/** 
	 * Undo the move on the game state. 
	 *
	 * @param   state    game tree state to be undone one move
	 */
	boolean undo (IGameState state);
	
	/** 
	 * Determine if move is valid in the game state.
	 * 
 	 * @param   state    game tree state to be inspected by move
	 */
	boolean isValid (IGameState state);
	
}
