package algs.model.problems.eightpuzzle;

import algs.model.searchtree.DepthTransition;
import algs.model.searchtree.INode;
import algs.model.searchtree.IScore;

/**
 * Weak evaluation function, as drawn from Nilsson, p. 56.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class WeakEvaluator implements IScore {

	/** Goal state. */
	static EightPuzzleNode goal = new EightPuzzleNode(new int[][]{
			{1,2,3},{8,0,4},{7,6,5}
	});
	
	/**
	 * @see algs.model.searchtree.IScore#score(INode)
	 */
	@Override
	public void score(INode state) {
		state.score(eval(state));
	}
	
	/**
	 * Eval = g(n) + W(n), where g(n) is length of the path from initial to
	 * node n, and W(n) counts number of misplaced tiles in the state description
	 * 
	 * @see algs.model.searchtree.IScore#score(INode)
	 * @param state     state being evaluated
	 */
	public int eval(INode state) {
		EightPuzzleNode node = (EightPuzzleNode) state;
		
		int misplaced = 0;
		for (int r = 0; r <= EightPuzzleNode.MaxR; r++) {
			for (int c = 0; c <= EightPuzzleNode.MaxC; c++) {
				if (node.isEmpty(r,c)) { continue; }
				
				if (node.cell(r, c) != goal.cell(r, c)) {
					misplaced++;
				}
			}
		}
		
		DepthTransition t = (DepthTransition) state.storedData();
		if (t == null) return misplaced;
		
		return t.depth + misplaced;
	}
}