package com.example.health_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;
import static com.github.mikephil.charting.charts.BarChart.*;

public class Data_Comparison extends AppCompatActivity {

    BarChart barChart1,barChart2,barChart3,barChart4;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    String str1,str2,str3,str4;
    public static float avgsl,avgcc,avgsr,avgdr,avgsll,avgccc,avgsrr,avgdrr;
    public static float a,b,c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        To_Do();
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
        getEntries(6,avgsl,a,barChart1,str1);
        getEntries(25,avgcc,b,barChart2,str2);
        getEntries(120,avgsr,c,barChart3,str3);
        getEntries(80,avgdr,d,barChart4,str4);
    }

    private void getEntries(float normal,float a1,float a2,BarChart barChart,String str) {
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, normal));
        barEntries.add(new BarEntry(2, a1));
        barEntries.add(new BarEntry(3, a2));
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinValue(0);
        barDataSet = new BarDataSet(barEntries,str);
        String[] report = new String[]{"normal", "average", "recent"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ChartAXisValueFormatter(report));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(barEntries.size());


        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.animateY(1000);
        barChart.setBackgroundColor(11110);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(15f);
        barChart.getDescription().setEnabled(false);

    }



    public void To_Do(){

        final String Email = Profile.Email;

        StringRequest stringRequest = new StringRequest(POST, Constant.url_personalInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    String[] content = response.toString().split("\n");
                    int ln = 0;
                    if(response.toString().trim().equalsIgnoreCase("no")) {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),"no data in your list",Toast.LENGTH_SHORT).show();
                    }
                    if (content.length > 15)
                        ln = 15;
                    else if (content.length > 0)
                        ln = content.length;

                    for (int i = 0; i < ln; i++) {
                        String[] element = content[i].split("-");

                        if(i==0){
                            a=Float.parseFloat(element[3]);
                            b=Float.parseFloat(element[4]);
                            c=Float.parseFloat(element[5]);
                            d=Float.parseFloat(element[6]);
                        }
                        avgsll+=Float.parseFloat(element[3]);
                        avgccc+=Float.parseFloat(element[4]);
                        avgsrr+=Float.parseFloat(element[5]);
                        avgdrr+=Float.parseFloat(element[6]);

                    }

                    avgsl=avgsll/ln;
                    avgcc=avgccc/ln;
                    avgdr=avgdrr/ln;
                    avgsr=avgsrr/ln;
                    avgsll=0;
                    avgccc=0;
                    avgdrr=0;
                    avgsrr=0;
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("Email",Email);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


}

