package algs.debug;

/**
 * Key information about a drawn graph can be represented as a graph legend.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class Legend implements IGraphEntity {
	/** The legend. */
	final String legend;
	
	/** Construct the legend contents from a String. */
	public Legend (String s) {
		legend = s;
	}
	
	/** Return label from the legend. */
	public String nodeLabel() {
		return legend;
	}

}
