package algs.model.tree;

import java.util.Iterator;

/**
 * Wraps a BinaryNode iterator to be able to extract in a type-safe way the values
 * of the BinaryNodes.
 * 
 * @param <T>     the base type of the values stored by the BinaryTree. Must be
 *
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class ValueExtractor<T> implements Iterator<T> {

	/** Store the Traversal Iterator from which we extract values. */
	@SuppressWarnings("unchecked")
	Iterator<BinaryNode> internal;
	
	/**
	 * ValueExtractor drains information from an iterator.
	 * 
	 * @param internal
	 */
	@SuppressWarnings("unchecked")
	ValueExtractor (Iterator<BinaryNode> internal) {
		this.internal = internal;
	}
	
	/** Delegate request to the internal iterator. */
	public boolean hasNext() {
		return internal.hasNext();
	}

	/** Delegate request to the internal iterator and extract its value. */
	@SuppressWarnings("unchecked")
	public T next() { return (T) internal.next().value; }
	
	/** Immutable iterator. */
	public void remove() { throw new UnsupportedOperationException("ValueExtractor Iterator can't be modified"); }
}