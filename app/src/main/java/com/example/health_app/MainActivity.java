package com.example.health_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signin, signup;
    private String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();
        OneSignal.setAppId(getApplication().getPackageName());

        setContentView(R.layout.activity_main);

        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);

        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.signin){

            SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);

            if(sharedPreferences.contains("Email") && sharedPreferences.contains("Password")){
                Profile.Email = sharedPreferences.getString("Email","nothing");
                Profile.name = sharedPreferences.getString("Name","nothing");
                Intent intent = new Intent(getApplicationContext(),Profile.class);
                //intent.putExtra("Email",Name);
                startActivity(intent);
                Log.d("ba " , "signin kam kore na kno?");
            }else{
                Log.d("ba " , "signin kam kore");
                Intent intent = new Intent(this,SignIn.class);
                startActivity(intent);
            }
        }
        else if(view.getId() == R.id.signup){
            Log.d("ba " , "signup kam kore");
            Intent intent = new Intent(this,SignUp.class);
            startActivity(intent);
        }

    }
}
