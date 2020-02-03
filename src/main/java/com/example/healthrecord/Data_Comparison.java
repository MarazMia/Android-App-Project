package com.example.healthrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

import static com.github.mikephil.charting.charts.BarChart.*;

public class Data_Comparison extends AppCompatActivity {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__comparison);
        barChart = findViewById(R.id.BarChart);
        getEntries();
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinValue(0);
        barDataSet = new BarDataSet(barEntries, "Sugar Level Information!!!");
        String[] report = new String[]{"normal", "average", "today"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ChartAXisValueFormatter(report));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(barEntries.size());


        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.animateY(1000);
        barChart.setBackgroundColor(11110);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(15f);
        barChart.getDescription().setEnabled(false);
    }

    private void getEntries() {
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 6f));
        barEntries.add(new BarEntry(2, Personal_info.avgsl));
        barEntries.add(new BarEntry(3, Profile.a));

    }

}

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

