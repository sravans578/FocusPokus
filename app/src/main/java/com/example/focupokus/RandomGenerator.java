package com.example.focupokus;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class RandomGenerator {
    Context context;
    String sample;

    public RandomGenerator(Context context, String sample) {
        this.context = context;
        this.sample = sample;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }



}
