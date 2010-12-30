package algs.model.tree.debug;

import algs.debug.DottyDebugger;
import algs.model.tree.BinaryNode;
import algs.model.tree.IVisitor;
import algs.model.tree.RightThreadedBinaryNode;

/**
 * Debugging subclass for right-threaded binary trees.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public class RightThreadTreeDebugger extends DottyDebugger implements IVisitor {

	/** 
	 * Default to having nodes with complex record shapes.
	 * 
	 * Subclasses should override as needed.
	 */
	public String edgeType() {
		return "dir=forward";
	}
	
	/**
	 * Visit right-threaded (parent, child) sequence by visiting both nodes separately, then the edge from
	 * parent to child, then any threaded edge, if one exists.
	 */
	@SuppressWarnings("unchecked")
	public void visit(BinaryNode parentNode, BinaryNode node) {
		RightThreadedBinaryNode parent = (RightThreadedBinaryNode) parentNode;
		RightThreadedBinaryNode n = (RightThreadedBinaryNode) node;
		
		// if not yet seen, visit node. Only have to check parent (for root).
		if (parent != null && !nodes.contains(parent)) {
			visitNode(parent);
		}
		
		if (!nodes.contains(n)) {
			visitNode(n);
		}
		
		if (parent != null) {
			visitEdge(parent, n);		
		}
		
		if (n.getNext() != null) {
			visitEdge (n, n.getNext());
		}
	}
}
