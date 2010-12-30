package algs.model.tree;

import algs.debug.IGraphEntity;


/**
 * Standard node for an unbalanced binary tree.
 * <p>
 * Each node has a 'key' which is used to determine location of the node
 * within the balanced binary tree. Each node also has a 'value' which
 * can be anything else.
 * <p>
 * Use the mutator methods CAREFULLY!
 *
 * @param <K>     the base type of the keys stored by each node.
 * @param <V>     the base type of the values stored by the BinaryNode.
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class BalancedBinaryNode<K,V> implements IGraphEntity {

	/** Left son. */
	protected BalancedBinaryNode<K,V> left;
	
	/** Right son. */
	protected BalancedBinaryNode<K,V> right;
	
	/** Parent. */
	protected BalancedBinaryNode<K,V> parent;
	
	/** Key. */
	K key;
	
	/** Value. */
	V value;
	
	/** Value to use when node is a RED node. */
    public static final boolean RED   = false;
    
    /** Value to use when node is a BLACK node. */
    public static final boolean BLACK = true;

    /** Color. */
	protected boolean color = BLACK;
	
	/**
	 * Set node color.
	 * 
	 * @param color    true for BLACK; false for RED.
	 */
	public void color(boolean color) {
		this.color = color;
	}
	
	/**
	 * Get node color.
	 * */
	public boolean color() {
		return color;
	}
	
	/**
     * Make a new node with given key, value, and parent, and with
     * <tt>null</tt> child links, and BLACK color.
     * 
	 * @param key
	 * @param value
	 * @param parent
	 */
    public BalancedBinaryNode(K key, V value, BalancedBinaryNode<K,V> parent) {
		
		this.key = key;
		this.parent = parent;
		
		left = null;
		right = null;
		parent = null;
		this.value = value;
	}

	/** Return left son. */
	public BalancedBinaryNode<K,V> left() {
		return left;
	}

	/** Return right son. */
	public BalancedBinaryNode<K,V> right() {
		return right;
	}
	
	/**
	 * Set the right child.
	 * 
	 * @param newRight  new node to be right child.
	 */
	public void right(BalancedBinaryNode<K,V> newRight) {
		right = newRight;
	}
	
	/**
	 * Set the left child.
	 * 
	 * @param newLeft    new node to be left child.
	 */
	public void left(BalancedBinaryNode<K,V> newLeft) {
		left = newLeft;
	}
	
	/**
	 * Get parent (needed for rotations and the like).
	 */
	public BalancedBinaryNode<K,V> parent() {
		return parent;
	}
	
	/**
	 * Set parent.
	 *
	 * @param newParent
	 */
	public void parent(BalancedBinaryNode<K,V> newParent) {
		parent = newParent;
	}
	
	/**
	 * Return the key for this node.
	 */
	public K key() {
		return key;
	}
	
	/**
	 * Return the value for this node.
	 */
	public V value() {
		return value;
	}
	
	 /**
     * Replaces the value currently associated with the key with the given
     * value.
     *
     * @return the value associated with the key before this method was
     *           called.
     */
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }
	
	/**
	 * Return string representation of this node.
	 */
	public String toString () {
		return key + "=" + value;
	}

	/** Confirm .equals but check for null too. */
    private boolean safeEquals (Object o1, Object o2) {
    	if (o1 == null && o2 == null) return true;
    	
    	if (o1 == null) return false;
    	if (o2 == null) return false;
    	return o1.equals(o2);
    }
    
    /**
     * Provide standard equals method.
     * 
     */
    @SuppressWarnings("unchecked")
	public boolean equals(Object o) {
        if (o instanceof BalancedBinaryNode) {
        	BalancedBinaryNode bbn = (BalancedBinaryNode) o;
        	
        	return safeEquals(key, bbn.key()) &&
        		safeEquals(value,bbn.value());
        }
        
        return false; // nope
    }

	@Override
	public String nodeLabel() {
		StringBuilder sb = new StringBuilder("{");
		if (color == RED) { sb.append ("red"); } else { sb.append ("black"); }
		sb.append ("|" + value + "}");
		return sb.toString();
	}
}
