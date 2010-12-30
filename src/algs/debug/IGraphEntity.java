package algs.debug;

import algs.model.gametree.MoveEvaluation;

/**
 * All graphical state drawing depends on the individual nodes to be able to return a labeled
 * string to represent itself.
 * 
 * We distribute the logic to the individual entities themselves, which simplifies
 * the drawing of debugging information at the expense of adding this extra method
 * to classes which otherwise should be unaware of the debugging infrastructure.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public interface IGraphEntity {

	/** Return string label for this entity. */
	String nodeLabel();

	/** Useful Format for special characters. */
	public static class Formatter {
		
		/**
		 * Helper method to determine if special font is needed.
		 */
		public static final boolean isSymbol (int value) {
			if (value == MoveEvaluation.minimum()) {
				return true;
			} else if (value == MoveEvaluation.maximum()) {
				return true;
			}

			return false;  // nothing special.			
		}
		
		/**
		 * Helper method for converting values into properly visible labels.
		 * 
		 * These special characters are displayed in Symbol font. 
		 */
		public static final String convert (int value) {
			if (value == MoveEvaluation.minimum()) {
				return "-¥";  // - INF using an em-dash
			} else if (value == MoveEvaluation.maximum()) {
				return "¥";   // that is char for INF in Symbol font.
			}

			return "" + value;
		}	
	}
}
