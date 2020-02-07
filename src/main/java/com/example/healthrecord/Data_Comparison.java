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

    BarChart barChart1,barChart2,barChart3,barChart4;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    String str1,str2,str3,str4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Personal_info pfobj = new Personal_info();
        pfobj.To_Do();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__comparison);
        barChart1 = findViewById(R.id.BarChart1);
        barChart2 = findViewById(R.id.BarChart2);
        barChart3 = findViewById(R.id.BarChart3);
        barChart4 = findViewById(R.id.BarChart4);
        str1="Sugar Level Information!!!";
        str2="Consumed Calories Information!!!";
        str3="Systolic Rate Information!!!";
        str4="Diastolic Rate Information!!!";
        getEntries(Personal_info.avgsl,Profile.a,barChart1,str1);
        getEntries(Personal_info.avgcc,Profile.b,barChart2,str2);
        getEntries(Personal_info.avgsr,Profile.c,barChart3,str3);
        getEntries(Personal_info.avgdr,Profile.d,barChart4,str4);

    }

    private void getEntries(float a1,float a2,BarChart barChart,String str) {
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 6f));
        barEntries.add(new BarEntry(2, a1));
        barEntries.add(new BarEntry(3, a2));
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinValue(0);
        barDataSet = new BarDataSet(barEntries,str);
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

}

