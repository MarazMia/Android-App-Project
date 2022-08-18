package com.example.health_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    private EditText email,password;
    private Button button;
    private ProgressBar loginprogress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        email = findViewById(R.id.signinEmail);
        password = findViewById(R.id.signinpass);
        button = findViewById(R.id.signinfinal);
        loginprogress = findViewById(R.id.loginprogress);
        button.setOnClickListener(this);


        SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);

        if(sharedPreferences.contains("Email") && sharedPreferences.contains("Password")){
            email.setText(sharedPreferences.getString("Email","nothing"));
            Profile.Email = sharedPreferences.getString("Email","nothing");
            password.setText(sharedPreferences.getString("Password","nothing"));

        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        login(view);

    }

    private void login(View view) {
        loginprogress.setVisibility(view.VISIBLE);
        final String Email = email.getText().toString().trim();
        final String loginPass = password.getText().toString().trim();

        if(Email.isEmpty()){
            this.email.setError("please enter your email");
            this.email.requestFocus();
            return;
        }

        if(loginPass.isEmpty()){
            this.password.setError("please enter your password");
            this.password.requestFocus();
            return;
        }


        StringRequest stringRequest = new StringRequest(POST, Constant.url_login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    String info[] =response.toString().split("\n");
                    if(info[0].equals("Successfully login")){
                        Profile.name = info[1];
                        loginprogress.setVisibility(View.GONE);
                        Profile.Email = Email;
                        SharedPreferences.Editor editor = getSharedPreferences("login", MODE_PRIVATE).edit();
                        editor.putString("Email",Email);
                        editor.putString("Password",loginPass);
                        editor.putString("Name", Profile.name);
                        editor.apply();

                        email.setText(null);
                        password.setText(null);

                        Intent intent = new Intent(getApplicationContext(),Profile.class);
                        //intent.putExtra("Email",Email);
                        startActivity(intent);
                    }else{
                        loginprogress.setVisibility(View.GONE);
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
                loginprogress.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("Email",Email);
                params.put("Password",loginPass);

                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}
