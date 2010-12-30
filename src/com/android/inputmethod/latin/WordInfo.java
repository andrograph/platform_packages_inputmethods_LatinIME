package com.android.inputmethod.latin;

import algs.model.nd.Hyperpoint;

public class WordInfo extends Hyperpoint {
    
    private String word;

    public WordInfo(double[] vals, String word) {
        super(vals);
        this.word = word;
    }
    
    public String getWord() {
        return word;
    }

}
