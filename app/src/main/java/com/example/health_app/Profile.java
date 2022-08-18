package com.example.health_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static com.android.volley.Request.Method.POST;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private EditText  date, consumed_calories,sugar_level,systolic_rate,diastolic_rate,weight,month,year;
    private TextView username;
    private Button submit;
    //private ProgressBar updateprogress;
    private DatePicker datePicker;
    private TimePicker timePicker;
    public static String Email, Date;
    private ProgressBar progressBar;
    public static String name;
    View view;
    public static String message;


    private Boolean exit = false;
    public static Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Bundle bundle = getIntent().getExtras();
        //Email = bundle.getString("Email");
        //Toast.makeText(getApplicationContext(),"welcome "+Profile.Email,Toast.LENGTH_LONG).show();

        progressBar = findViewById(R.id.update_pro);
        username = findViewById(R.id.username);
        username.setText(name);
        datePicker = findViewById(R.id.picdate);
        timePicker = findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);
        consumed_calories = findViewById(R.id.consumed_calories);
        month = findViewById(R.id.month);
        date = findViewById(R.id.date);
        year = findViewById(R.id.year);
        systolic_rate = findViewById(R.id.systolicRate);
        diastolic_rate = findViewById(R.id.diastolicRate);
        weight = findViewById(R.id.weight);
        sugar_level = findViewById(R.id.sugarLevel);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
        date.setOnClickListener(this);
        month.setOnClickListener(this);
        year.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){

            SharedPreferences settings = getSharedPreferences("login", Context.MODE_PRIVATE);
            settings.edit().clear().commit();

            this.finish();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }


        else if(item.getItemId() == R.id.myinfo){
            Intent intent = new Intent(getApplicationContext(),Personal_info.class);
            startActivity(intent);
        }

        else if(item.getItemId() == R.id.stepcounter){
            Intent intent = new Intent(getApplicationContext(),StepCounter.class);
            startActivity(intent);
        }

        else if(item.getItemId() == R.id.notification){
            Intent intent = new Intent(getApplicationContext(),Notification.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.about){
            Intent intent = new Intent(getApplicationContext(),About.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.preview){
            Intent intent = new Intent(getApplicationContext(),SearchRecord.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.comparison){
            Intent intent = new Intent(getApplicationContext(),Data_Comparison.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.date){
            date.setText(currentDate());
        }

        if(view.getId() == R.id.month){
            month.setText(currentMonth());
        }


        if(view.getId() == R.id.year){
            year.setText(currentYear());
        }


        if(view.getId() == R.id.submit){
            update(Date,view);
        }


    }


    String currentYear(){
        StringBuilder date = new StringBuilder();
        StringBuilder dateForAutoUpdate = new StringBuilder();
        date.append(datePicker.getYear());
        dateForAutoUpdate.append(datePicker.getYear());
        return date.toString();
    }

    String currentMonth(){
        StringBuilder month = new StringBuilder();
        StringBuilder dateForAutoUpdate = new StringBuilder();
            month.append(""+(datePicker.getMonth()+1)+"");
            dateForAutoUpdate.append(""+(datePicker.getMonth()+1)+"");
            String Month = month.toString();
            //Toast.makeText(getApplicationContext(),Month,Toast.LENGTH_SHORT).show();
            if(Month.equals("1")){
                Month="JANUARY";
            }
            else if(Month.equals("2"))
                Month="FEBRUARY";
            else if(Month.equals("3"))
                Month="MARCH";
            else if(Month.equals("4"))
                Month="APRIL";
            else if(Month.equals("5"))
                Month="MAY";
            else if(Month.equals("6"))
                Month="JUNE";
            else if(Month.equals("7"))
                Month="JULY";
            else if(Month.equals("8"))
                Month="AUGUST";
            else if(Month.equals("9"))
                Month="SEPTEMBER";
            else if(Month.equals("10"))
                Month="OCTOBER";
            else if(Month.equals("11"))
                Month="NOVEMBER";
            else if(Month.equals("12"))
                Month="DECEMBER";
        return Month;
    }

    String currentDate(){
        StringBuilder date = new StringBuilder();
        StringBuilder dateForAutoUpdate = new StringBuilder();
        if(datePicker.getDayOfMonth()<10){
            date.append("0"+datePicker.getDayOfMonth()+"/");
            dateForAutoUpdate.append("0"+(datePicker.getDayOfMonth()-1)+"/");
        }else {
            date.append(datePicker.getDayOfMonth() + "/");
            dateForAutoUpdate.append(datePicker.getDayOfMonth()-1 + "/");
        }
        if(datePicker.getMonth() <10){
            date.append("0"+(datePicker.getMonth()+1)+"/");
            dateForAutoUpdate.append("0"+(datePicker.getMonth()+1)+"/");
        }else{
            date.append((datePicker.getMonth()+1)+"/");
            dateForAutoUpdate.append((datePicker.getMonth()+1)+"/");
        }
        date.append(datePicker.getYear());
        dateForAutoUpdate.append(datePicker.getYear());
        Date = dateForAutoUpdate.toString();
        return date.toString();
    }


    private void update(String datepic, View view) {

        progressBar.setVisibility(View.VISIBLE);

        final String Date = date.getText().toString().trim();
        final String SugarLevel = sugar_level.getText().toString().trim();
        final String ConsumedCalories = consumed_calories.getText().toString().trim();
        final String DiastolicRate = diastolic_rate.getText().toString().trim();
        final String SystolicRate = systolic_rate.getText().toString().trim();
        final String Weight = weight.getText().toString().trim();
        final String Month = month.getText().toString().trim();
        final String Year = year.getText().toString().trim();


        if(Year.isEmpty()){
            year.setError("please enter your year");
            year.requestFocus();
            return;
        }

        if(Month.isEmpty()){
            month.setError("please enter your date");
            month.requestFocus();
            return;
        }

        if(Date.isEmpty()){
            date.setError("please enter your date");
            date.requestFocus();
            return;
        }

        if(SugarLevel.isEmpty()){
            sugar_level.setError("please enter your sugar level");
            sugar_level.requestFocus();
            return;
        }

        if(ConsumedCalories.isEmpty()){
            consumed_calories.setError("please enter today's consumed calories");
            consumed_calories.requestFocus();
            return;
        }

        if(SystolicRate.isEmpty()){
            systolic_rate.setError("please enter your low blood pressure");
            diastolic_rate.requestFocus();
            return;
        }

        if(DiastolicRate.isEmpty()){
            diastolic_rate.setError("please enter high blood pressure");
            diastolic_rate.requestFocus();
            return;
        }

        if(Weight.isEmpty()){
            weight.setError("please enter your weight");
            weight.requestFocus();
            return;
        }
        if( !Month.equalsIgnoreCase("january") && !Month.equalsIgnoreCase("february") && !Month.equalsIgnoreCase("march") && !Month.equalsIgnoreCase("april")
                && !Month.equalsIgnoreCase("may") && !Month.equalsIgnoreCase("june") && !Month.equalsIgnoreCase("july") && !Month.equalsIgnoreCase("august")
                && !Month.equalsIgnoreCase("september") && !Month.equalsIgnoreCase("october") && !Month.equalsIgnoreCase("november") && !Month.equalsIgnoreCase("december")){
            month.setError("please check your spelling");
            month.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(POST, Constant.url_updateinfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    if(response.toString().equals("Updated successfully")){



                        progressBar.setVisibility(View.GONE);
                        setNotification();
                        year.setText(null);
                        month.setText(null);
                        date.setText(null);
                        sugar_level.setText(null);
                        consumed_calories.setText(null);
                        systolic_rate.setText(null);
                        diastolic_rate.setText(null);
                        weight.setText(null);


                    }else{
                        progressBar.setVisibility(View.GONE);
                    }
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

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
                String UpMonth;
                UpMonth=Month.toUpperCase();
                Map<String,String> params= new HashMap<>();
                params.put("Email", Email);
                params.put("Year", Year);
                params.put("Month", UpMonth);
                params.put("Date",Date);
                params.put("Sugar_level",SugarLevel);
                params.put("CC",ConsumedCalories);
                params.put("SR",SystolicRate);
                params.put("DR",DiastolicRate);
                params.put("Weight",Weight);

                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }


    public void setNotification(){

        String tk =sugar_level.getText().toString().trim();
        String Message;
        String title;
            Message = "Hey "+name+"!! You have update your details.\n"+"You have taken "+consumed_calories.getText().toString().trim()+" kilo calories today..." +
                    "\nyour sugar level was "+tk+" mili mol per " +
                    "liter.\nYour blood pressure was "+systolic_rate.getText().toString().trim()+"/"+diastolic_rate.getText().toString().trim();
            title = "profile updated";
        message = Message;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)

        .setSmallIcon(R.drawable.ic_message_black_24dp)
        .setContentTitle(title)
        .setContentText(message)
        .setAutoCancel(true);
        builder.setVibrate(new long[]{0, 500, 1000});
        builder.setLights(0xff0000ff, 300, 1000);

        Intent intent = new Intent(this,Notification.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Message",message);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);


        NotificationManager notificationManager =(NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE
        );

        notificationManager.notify(0,builder.build());
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                    Intent a = new Intent(Intent.ACTION_MAIN);
                    a.addCategory(Intent.CATEGORY_HOME);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(a);
                }
            },  10);

        }

    }
}
