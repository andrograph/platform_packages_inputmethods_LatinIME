package algs.model.gametree.debug;

import algs.debug.IGraphEntity;
import algs.debug.ISelectFont;

/**
 * Represents a NegMax node in the debugging output.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class NegMaxNode implements IGraphEntity, ISelectFont {
	/** Computed value for this node. */
	int value;
	
	/** Return the computed value. */
	public int value() {
		return value;
	}
	
	/** Set the computed value. */
	public void value (int value) {
		this.value = value;
	}

	/** Return node label that properly shows value. */
	public String nodeLabel() {
		return "NEGMAX: " + Formatter.convert(value);
	}
	
	/** To properly draw Negmax node in symbol font. */
	@Override
	public String fontName() {
		if (Formatter.isSymbol(value)) {
			return "Symbol";
		}
		
		return null;  // nothing special.
	}

	/** Default font size to use is ok. */
	@Override
	public int fontSize() {
		return 0;
	}
}
