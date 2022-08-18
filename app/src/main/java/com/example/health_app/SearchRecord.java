package com.example.health_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchRecord extends AppCompatActivity implements View.OnClickListener {

    public EditText year,month;
    public Button search;
    public static String searchYear,searchMonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_record);
        year=(EditText) findViewById(R.id.searchYear);
        month=(EditText) findViewById(R.id.searchMonth);
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SearchData(view);
        year.setText(null);
        month.setText(null);
    }
    public void SearchData(View v){
        searchYear=year.getText().toString().trim();
        searchMonth=month.getText().toString().trim().toUpperCase();
        if(searchYear.isEmpty()){
            year.setError("please enter your year");
            year.requestFocus();
            return;
        }

        if(searchMonth.isEmpty()){
            month.setError("please enter your month");
            month.requestFocus();
            return;
        }
        Intent intent = new Intent(getApplicationContext(),PreviewInfo.class);
        startActivity(intent);
    }
}
