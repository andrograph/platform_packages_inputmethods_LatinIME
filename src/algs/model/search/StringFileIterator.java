package algs.model.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Return an Iterator<String> for the strings in a File.
 * 
 * Useful to populate a Hashtable with initial values.
 * 
 * @author George Heineman
 * @author Gary Pollice
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class StringFileIterator implements Iterator<String> {

	/** Use scanner to process file. If null, then no elements. */
	Scanner sc;
	
	/** 
	 * On constructor set up the Scanner, if possible.
	 * 
	 * @param f   File from which to read strings, one per line.
	 */
	public StringFileIterator(File f) {
		if (f.exists()) {
			try {
				sc = new Scanner(f);
			} catch (FileNotFoundException e) {
				// shouldn't happen since we checked for existence, but...
				sc = null;
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if (sc == null) { return false; }
		
		return sc.hasNextLine();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public String next() throws NoSuchElementException {
		if (sc == null) {
			throw new NoSuchElementException ("End of file reached.");
		}
		
		String s = sc.nextLine();
		
		// actively take steps to close down.
		if (!sc.hasNextLine()) {
			sc.close();
			sc = null;
		}
		
		return s;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException("Unable to delete from StringFileIterator.");
	}
}
