package com.example.health_app;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

class ChartAXisValueFormatter implements IAxisValueFormatter {
    private String[] mValues;

    public ChartAXisValueFormatter(String[] values) {
        mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        int val = (int) (value) -1;
        String label = "";
        if (val >= 0 && val < mValues.length) {
            label = mValues[val];
        } else {
            label = "";
        }
        return label;
    }
}
