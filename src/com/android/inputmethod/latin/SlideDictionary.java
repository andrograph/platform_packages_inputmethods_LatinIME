package com.android.inputmethod.latin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import algs.model.kdtree.KDFactory;
import algs.model.kdtree.KDTree;
import algs.model.nd.Hyperpoint;
import android.util.Log;

public class SlideDictionary {
    
    private KDTree tree;
    
    public SlideDictionary(File dictfile) throws IOException {
        List<WordInfo> words = new ArrayList<WordInfo>();
        BufferedReader reader = new BufferedReader(new FileReader(dictfile));
        String line;
        long startTime = System.currentTimeMillis();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("\t");
            double[] coords = new double[data.length-1];
            for (int i = 1;i<data.length;i++) {
                coords[i-1] = Double.parseDouble(data[i]);
            }
            coords[0] = coords[0];
            WordInfo p = new WordInfo(coords, data[0]);
            //Log.i("SlideDictionary", "read word " + data[0] + " with " + p.raw().length + " dimensions");
            words.add(p);
            
        }
        long endTime = System.currentTimeMillis();
        Log.i("SlideCitionary", "reading " + words.size() + " took " + (endTime - startTime) + " millis");
        tree = KDFactory.generate(words.toArray(new WordInfo[]{}));
    }

    public String getWordSuggestion(double[]x, double[]y) {
        double[] params = WordHash.getSlideParameters(x, y);
        Log.i("SlideDictionary", "Parameters");
        logarr(params);
        WordInfo nearest = (WordInfo)tree.nearest(new Hyperpoint(params));
        Log.i("SlideDictionary", "nearest match:");
        logarr(nearest.raw());
        return nearest.getWord();
    }
    
    public void logarr(double[]arr) {
        StringBuilder t = new StringBuilder();
        for (int i=0;i<arr.length;i++) {
            t.append(String.format("%2.2f ", arr[i]));
        }
        Log.i("SlideDictionary", t.toString());
    }
    

    
}
