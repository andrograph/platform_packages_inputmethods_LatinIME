package algs.model;

/**
 * A segment has a left and a right index and is understood to represent a semi-closed
 * range [left, right). In otherwords, the value 'left' intersects this segment while
 * the value 'right' does not.
 * 
 * To be a proper segment, the left value must be strictly less than the right value.
 * While this is assumed to be an invariant, code that depends upon IInterval may choose
 * to check this assumption where appropriate.
 *
 * Implementations of this class must provide {@link Object#equals(Object)}, 
 * {@link Object#hashCode()} and {@link Object#toString()}.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public interface IInterval {
	
	/**
	 * Return left index.
	 * 
	 * @return left index whose value is strictly less than right index
	 */
	int getLeft();
	
	/**
	 * Return right index.
	 *
	 * @return right index whose value is strictly greater than left index
	 */
	int getRight();
	
	/**
	 * Determines if the q value is strictly less than the getLeft() value.
	 * 
	 * @param q
	 */
	boolean toTheLeft (int q);
	
	/**
	 * Determines if the q value is greater than or equal to the getRight() value.
	 * 
	 * @param q
	 */
	boolean toTheRight (int q);
	
	/**
	 * Determines if the q value is greater than or equal to getLeft() and strictly 
	 * less than getRight()
	 * 
	 * @param q
	 */
	boolean intersects (int q);
}
