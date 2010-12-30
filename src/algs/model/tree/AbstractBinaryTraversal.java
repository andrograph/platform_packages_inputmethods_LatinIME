package algs.model.tree;

import java.util.Stack;
import java.util.Iterator;

import algs.model.IBinaryTreeNode;

/**
 * The default traversal class for IBinaryTree trees.
 * 
 * Makes it possible to expose a pre-, in-, or post-order traversal as an Iterator.
 *<p>
 * @param <T>  Any class that extends {@link IBinaryTreeNode} can be used as the 
 * structure of a Binary tree, and thus can be used as the parameter for this traversal
 * class.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public abstract class AbstractBinaryTraversal<T extends IBinaryTreeNode> implements Iterator<IBinaryTreeNode> {

	/** Binary traversals have three phases. */
	public enum Phase { LEFT, SELF, RIGHT, DONE };
	
	/** A moment in the computation of the traversal. */
	class Moment {
		IBinaryTreeNode node;
		
		Phase phase;
		
		Moment (T node, Phase phase) {
			this.node = node;
			this.phase = phase;
		}
		
		public String toString () {
			return "[" + node + " @ " + phase + "]";
		}
	}
	
	/**
	 * Current point in the computation. 
	 *
	 * The current node is always the top one on the stack (if stack is empty we are done).
	 * 
	 * Make private so subclasses can't mess it up. 
	 */
	private Stack<Moment> stack;
	
	/**
	 * Start the traversal at the given node.
	 * 
	 * @param node
	 */
	public AbstractBinaryTraversal(T node) {
		stack = new Stack<Moment>();
		stack.add(new Moment (node, initialPhase()));
		
		// advance as far as we need to until we have a value to be returned.
		advance();
	}


	/** 
	 * Return the initial phase of the traversal.
	 * 
	 * Essential for the generic traversal routine in advance()
	 */
	public abstract Phase initialPhase();
	
	/** 
	 * Return the final phase of the traversal.
	 * 
	 * Essential for the generic traversal routine in advance()
	 */
	public abstract Phase finalPhase();
	
	/**
	 * Determine the next phase in the traversal.
	 * 
	 * Essential for the generic traversal routine in advance()
	 */
	public abstract Phase advancePhase (Phase p);
	
	/**
	 * Advance the traversal, returning the SELF node once found or <code>null</code> when all is done.
	 */
	@SuppressWarnings("unchecked")
	public void advance() {
		if (stack.isEmpty()) { return; }
		
		Moment m = stack.peek();
		
		// build up stack and keep track of the phase of the computation at each of the
		// inner nodes.
		while (m != null) {
			switch (m.phase) {
				case DONE:
					stack.pop();
					if (stack.isEmpty()) { return; }   // DONE
					m = stack.peek();
					m.phase = advancePhase(m.phase);  // when moving forward, we update phase (right?)
					break;
			
				case LEFT:
					if (m.node.getLeftSon() != null) {
						m = new Moment ((T) m.node.getLeftSon(), initialPhase());
						stack.push(m);
					} else {
						// advance before returning node.
						m.phase = advancePhase (m.phase);
					}
					break;
	
				case SELF:
					// advance before completing the phase.
					m.phase = advancePhase (m.phase);
					return;
					
				case RIGHT:
					if (m.node.getRightSon() != null) {
						m = new Moment ((T)m.node.getRightSon(), initialPhase());
						stack.push(m);
					} else {
						// advance before returning node.
						m.phase = advancePhase (m.phase);
					}
					break;
			}
		}

	}

	/**
	 * Determines if there are more steps in the traversal.
	 */
	public boolean hasNext() {
		if (stack.isEmpty()) { return false; }
		
		return true;
	}

	/**
	 * Returns the next node in the traversal. 
	 */
	public IBinaryTreeNode next() {
		
		// there must be something on the stack.
		if (stack.empty()) {
			throw new java.util.NoSuchElementException("Binary Traversal Iterator has no more elements");			
		}
		
		Moment m = stack.peek();
		
		// now advance.
		advance();

		// return the most recent one.
		return m.node;
	}

	/** Not supported. */
	public void remove() {
		throw new java.lang.UnsupportedOperationException("Binary Traversal Iterator does not support removals.");
	}
		
}
