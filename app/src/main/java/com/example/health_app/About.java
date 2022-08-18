package com.example.health_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class About extends AppCompatActivity {
    public TextView normal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        normal =(TextView) findViewById(R.id.normal);
        normal.setText("Sugar Level-->4 to 6.5 mili mol per Liter\n" +
                "Consumed Calories-->24 kiloCal(for avg. female)/\n28 kiloCal(for avg. male)\n" +
                "Systolic Rate-->120 mmHg\n" +
                "Diastolic Rate-->80mmHg" +
                "Weight-->45 to 55 kg(for avg. female)/\n 60 to 75 kg(for avg. male)");
    }
}
