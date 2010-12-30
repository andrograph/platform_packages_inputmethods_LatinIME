package algs.model.problems.tictactoe.model;

import java.util.Collection;

import algs.model.gametree.IGameState;
import algs.model.gametree.IGameMove;
import algs.model.gametree.IPlayer;
import algs.model.gametree.IGameScore;

/**
 * Represents a Player in a Tic Tac Toe variation.
 * <p>
 * For maximum flexibility, each player is associated with an IScore scoring 
 * function so different players can have different strategies for playing games.
 * This enables an AlphaBeta player to go up against a MiniMax player, for example.
 * The game tree algorithms would otherwise be more complicated.
 * <p>
 * Make sure that you assign an effective scoring function with each player, 
 * otherwise the move search algorithms will not perform effectively.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public abstract class Player implements IPlayer {
	/** Knows its character to play. */
	protected char mark;
	
	/** Global access to OMark. */
	public static final char OMARK = 'O';

	/** Global access to XMark. */
	public static final char XMARK = 'X';

	/** Scoring method used. */
	protected IGameScore score;
	
	/** Logic for game. */
	protected Logic logic;
	
	/**
	 * Return the mark for the player.
	 * 
	 * @return   char representing the mark. Will never be ' '.
	 */
	public char getMark () {
		return mark;
	}
	
	/**
	 * Return opponent mark.
	 * @return   char representing the mark of the opponent. Will never be ' ' or mark.
	 */
	public char getOpponentMark () {
		if (mark == OMARK) {
			return XMARK;
		}
		
		// we must be XMARK, so our opponent is OMARK.
		return OMARK;
	}
	
	/**
	 * Creates a player with a certain mark.
	 * <p> 
	 * Installs default scoring method that evaluates all boards as 0.
	 * 
	 * @param mark      Mark to be played (cannot be ' ')
	 * @exception  IllegalArgumentException     if ' ' is used as the mark.
	 */
	public Player (char mark) {
		if (mark == ' ') {
			throw new IllegalArgumentException ("Player cannot have space (' ') as a mark.");
		}
		
		this.mark = mark;
		
		this.score = new IGameScore() {
			public int score(IGameState state, IPlayer player) {
				return 0;
			}
		};
	}
	
	/**
	 * Decide upon a move with the given board state. 
	 * <p>
	 * If the player decides to forfeit the game, return null; otherwise
	 * a valid Move object is returned that can be played on the given
	 * board state.
	 * 
	 * @param board
	 * @return          valid Move object, otherwise null to forfeit game.
	 */
	public abstract IGameMove decideMove (IGameState board);
	
	/**
	 * Reasonable toString method, revealing logic for player.
	 */
	public String toString () {
		return "Player [" + mark + "]";
	}

	/** 
	 * Return the evaluation of this game state from the perspective of
	 * the given player.
	 * 
	 * If no evaluation function is in place, then return 0.
	 */
	public int eval (IGameState state) {
		if (score == null) { return 0; }
		
		return score.score(state, this);
	}
	
	/** Returns logic. */
	public Logic logic() {
		return logic;
	}
	
	/** Set the logic of the game being played. */
	public void logic (Logic logic) {
		this.logic = logic;
	}
	
	/** Returns scoring method. */
	public IGameScore score() {
		return score;
	}

	/** Set the scoring method to use. */
	public void score(IGameScore score) {
		this.score = score;
	}

	/**
	 * Enable Player objects to be used in hash table.
	 * <p>
	 * Note that equals is restricted to be '==' since we do not override .equals
	 */
	public int hashCode() {
		return toString().hashCode();
	}
	
	/**
	 * Return the valid moves for this player given the game state. 
	 * 
	 * @param state   Current game state position
	 */
	public Collection<IGameMove> validMoves(IGameState state) {
		return logic.validMoves(this, state);
	}
}
