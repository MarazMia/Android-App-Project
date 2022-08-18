package com.example.health_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
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

public class PreviewInfo extends AppCompatActivity {

    private WebView webView;
    private String htmlMiddle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        webView = findViewById(R.id.webview_preview);

        preview_info();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void preview_info(){
        final String Email = Profile.Email;
        final String Year = SearchRecord.searchYear;
        final String Month = SearchRecord.searchMonth;

        StringRequest stringRequest = new StringRequest(POST, Constant.url_search, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    if(response.toString().trim().equalsIgnoreCase("no")) {
                        String htmlTable = Constant.htmlEmpty;
                        webView.loadDataWithBaseURL(null, htmlTable, "text/html", "utf-8", null);

                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),"no data in your list",Toast.LENGTH_SHORT).show();
                    }
                    String[] content = response.toString().split("\n");
                    for(int i=0; i<content.length; i++){
                        String[] element = content[i].split("-");

                        htmlMiddle +=   "   <tr>\n" +
                                "<td>"+element[0]+"</td>\n" +
                                "<td>"+element[1]+"</td>\n" +
                                "<td>"+element[2]+"</td>\n" +
                                "<td>"+element[3]+"</td>\n" +
                                "<td>"+element[4]+"</td>\n" +
                                "<td>"+element[5]+"</td>\n" +
                                "<td>"+element[6]+"</td>\n" +
                                "<td>"+element[7]+"</td>\n" +
                                "  </tr>\n" ;
                    }

                    String htmlTable = Constant.html+htmlMiddle+Constant.htmlLast;
                    webView.loadDataWithBaseURL(null,htmlTable,"text/html","utf-8",null);

                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                 }catch (JSONException e) {
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
                params.put("Year",Year);
                params.put("Month",Month);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}

