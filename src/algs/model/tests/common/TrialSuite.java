package algs.model.tests.common;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;


/**
 * Represents a suite of timed trials.
 * <p>
 * Automatically throws away the lowest and highest of all trials sizes when
 * computing the average.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class TrialSuite {
	
	/** Trials grouped by size n. */
	TreeMap<Long,ArrayList<Long>> timings = new TreeMap<Long,ArrayList<Long>>();
	
	private String buildHeader() {
		return "n,average,min,max,stdev,#";
	}
	
	@SuppressWarnings("unchecked")
	private String buildTable(long n) {
		ArrayList<Long> times = timings.get(n);
		long min, max;
		Hashtable<Long,HistPair> table = new Hashtable<Long,HistPair>();
		
		min = max = times.get(0);
		HistPair pair = new HistPair(min, 1);
		table.put (min, pair);
		
		// compute number of independent solutions.
		for (int i = 1; i < times.size(); i++) {
			long t = times.get(i);
			pair = table.get(t);
			if (pair == null) {
				table.put (t, new HistPair(t, 1));
			} else {
				pair.addCount();
			}
			
			if (t < min) { min = t; }
			if (t > max) { max = t; }
		}
		
		// pull out and sort
		Comparable[]vals = new Comparable[0];
		vals = table.values().toArray(vals);
		// vals could be sorted?
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < vals.length; i++) {
			sb.append(vals[i]).append("\n");
		}
		return sb.toString();
	}
	
	private String buildRow(long n) {
		ArrayList<Long> times = timings.get(n);
		
		long sum, min, max;

		if (times == null || times.size() == 0) { return n + ",*,*,*,*,0"; }
		
		sum = 0;
		min = max = sum = times.get(0);
		
		if (times.size() == 1) { return n + "," + min + "," + min + "," + max + "," + 0 + "," + 1; }

		int minIdx = 0;
		int maxIdx = 0;
		for (int i = 1; i < times.size(); i++) {
			long t = times.get(i);
			if (t < min) { min = t; minIdx = i;}
			if (t > max) { max = t; maxIdx = i;}
			sum += t;
		}
		
		if (times.size() == 2) {
			double diff = Math.abs(max - min);
			diff = diff / 2;
			double avg = max + min;
			avg = avg / 2;
			return n + "," + avg + "," + min + "," + max + "," + diff + "," + 2; 
		}
	
		// throw away lowest and highest.
		sum = sum - min - max;
		int ct = times.size() - 2;

		double mean = sum;
		mean = mean / ct;
		double calc = 0;
		for (int i = 0; i < times.size(); i++) {
			if (i == minIdx || i == maxIdx) continue;
			calc += (times.get(i) - mean)*(times.get(i) - mean);
		}
		//sqrt((1/[n-1])*sum[(xi-mean)^2]) FORMULA FROM EXCEL SPREADSHEET
		calc /= (ct-1);
		calc = Math.sqrt(calc);
		
		return n + "," + mean + "," + min + "," + max + "," + calc + "," + ct;
	}
	
	/**
	 * Expose the set of keys maintained by the table.
	 */
	public Iterator<Long> keys() {
		return timings.keySet().iterator();
	}
	
	/**
	 * Return a single average, if it exists in the table.
	 * 
	 * @param n   the desired row.
	 */
	public String getAverage(long n) {
		String row = buildRow(n);
		if (row.equals("")) { return ""; }
		
		StringTokenizer st = new StringTokenizer(row, ",");
		if (st.hasMoreTokens()) {
			st.nextToken(); // skip n
			if (st.hasMoreTokens()) { return st.nextToken(); }  // got average!
		}

		return "";  // nothing to return.
	}
	
	/**
	 * Return a single row, if it exists in the table.
	 * 
	 * @param n   the desired row.
	 */
	public String getRow(long n) {
		Iterator<Long> it = timings.keySet().iterator();
		while (it.hasNext()) {
			long existing = it.next();
				
			if (existing == n) {
				return buildRow(n);
			}
		}
		
		return "";  // nothing to return.
	}
	
	/** Produce full table of information. */
	public String computeTable() {
		StringBuilder sb = new StringBuilder();
		sb.append(buildHeader());
		sb.append("\n");
		
		Iterator<Long> it = timings.keySet().iterator();
		while (it.hasNext()) {
			long n = it.next();
			
			sb.append(buildRow(n));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public String histogram() {
		StringBuilder sb = new StringBuilder();
		sb.append(buildHeader());
		sb.append("\n");
		
		Iterator<Long> it = timings.keySet().iterator();
		while (it.hasNext()) {
			long n = it.next();
			
			sb.append("Table for:" + n).append("\n");
			sb.append("---------------------------------------\n");
			sb.append(buildTable(n));
			sb.append("---------------------------------------\n");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
	
	
	/**
	 * Record the timing of a trial of size n that started at startTime and 
	 * completed at endTime.
	 * 
	 * @param n
	 * @param startTime
	 * @param endTime
	 */
	public void addTrial (long n, long startTime, long endTime) {
		ArrayList<Long> trials = timings.get(n);
		if (trials == null) {
			trials = new ArrayList<Long>();
			timings.put(n,trials);
		}
		
		// add to the set.
		trials.add(new Long(endTime-startTime));
	}

	
	
	
}
