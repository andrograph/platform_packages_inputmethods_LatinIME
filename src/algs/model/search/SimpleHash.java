package algs.model.search;

/**
 * Implements standard java.lang.String hash computation on String objects.
 * 
 * @author George Heineman
 * @author Gary Pollice
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class SimpleHash implements IHash<String> {

	/** Size of the table. */
	int tableSize;
	
	/** hash needs to know size of the table. */
	public SimpleHash (int tableSize) {
		this.tableSize = tableSize;
	}
	
	@Override
	public int hash(String s) {
		int h = 0;
		for (int i = 0; i < s.length(); i++) {
			h = (h << 1) + s.charAt(i);
		}
		
		if (h < 0) h = 0 - h;
		return h % tableSize;
	}

}
