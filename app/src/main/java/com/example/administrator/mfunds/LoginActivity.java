package com.example.administrator.mfunds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Toast;



import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Button;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

//import com.fasterxml.jackson.databind.ObjectMapper;// in play 2.3


//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;




import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;







public class LoginActivity extends AppCompatActivity {
    private EditText txtUserName;
    private EditText txtPassword;
    private TextView loginButton;
    private TextView registerButton;
    private Button btn_fund;

    private String username;
    private String password;
    private String data;
    boolean isValid=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        txtUserName = (EditText) findViewById(R.id.txtUser);
        loginButton = (TextView) findViewById(R.id.btnLogin);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        registerButton = (TextView) findViewById(R.id.btnRegister);






        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                login();

            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();



            }
        });



    }




    public void PostJsonObject(String param_url, final String func) {


        RequestQueue queue = Volley.newRequestQueue(this);
        //RequestQueue queue = .getRequestQueue();

        //  Map<String, String> jsonParam = new HashMap<String, String>();
        //  jsonParam.put("title", "Deep Learning");
        JSONObject json = new JSONObject();
      //  Toast.makeText(getApplicationContext(), param_url, Toast.LENGTH_SHORT).show();

        //    try {
        //        json.put("user", "");
        //        } catch (JSONException e) {
        //        e.printStackTrace();
        //    }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                //   Url, new JSONObject(jsonParam),
                param_url, json,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try{

                            jsonParse(response,func);

                        }catch(Exception e){


                            e.printStackTrace();

                        }


                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("charset", "utf-8");
                return headers;
            }
        };

// Add the request to the RequestQueue.
        queue.add(jsonObjReq);



    }




    private void login () {



        username ="";
        username ="";

        username = txtUserName.getText().toString();
        password = txtPassword.getText().toString();


        String Url = "http://charlie3.gear.host/MyService.svc/apiLogin/{username:'"+username+"',password:'"+password+"'}";

        Toast.makeText(getApplicationContext(), "password: " + password, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "username: " + username, Toast.LENGTH_SHORT).show();


        connectAsync(Url,"validateLoginResult");

        isTrue gotStatus = isTrue.getInstance();

        boolean test = false;

        test = gotStatus.getValue();

        if(test){




        }











    }




    private boolean jsonParse(JSONObject j,String str){


        String obj="";
        if(str.equals("registerResult")){

            obj = "registerResult";

        }else if(str.equals("validateLoginResult")){

            obj = "validateLoginResult";
        }



        String result;
        try {


            result  = j.getString(obj);

            if(result.equals("true"));
            isValid=true;



        } catch(JSONException e) {

            isValid = false;
            e.printStackTrace();
        }


        return isValid;
    }





    protected void connectAsync(String url,final String func) {



        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        isValid = false;
        // valid="";

        //final String url = "http://charlie-davidwillock.c9users.io/articles/new?title=mobile&text=testapi";

        client.get(url, params, new TextHttpResponseHandler() {

                    // String valid="";
                    isTrue getStatus = isTrue.getInstance();

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"
                        try {
                            String valid="";
                            JSONObject obj = new JSONObject(res);
                            valid = obj.getString(func);
                            if(valid.equals("true")){

                                getStatus.setValue(true);
                                Toast.makeText(getApplicationContext(), "Password correct", Toast.LENGTH_SHORT).show();
                            }else{

                                getStatus.setValue(false);
                                Toast.makeText(getApplicationContext(), "Password incorrect", Toast.LENGTH_SHORT).show();
                            }
                            //  Toast.makeText(getApplicationContext(), "Successful connection " , Toast.LENGTH_SHORT).show();



                        } catch (JSONException ex) {

                            ex.printStackTrace();
                        }



                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        String failure = "Cannot connect via http";
                        Toast.makeText(getApplicationContext(),
                                "Connection failure", Toast.LENGTH_SHORT).show();
                        getStatus.setValue(false);



                    }
                }

        );




    }










}

class getFundValues {

    private double fvalue;
    private double[] values;

    private static com.example.administrator.mfunds.getFundValues instance=null;

    double getValue(){

        return fvalue;
    }

    void setValue(double f){

        this.fvalue = f;
    }

    private void data() {

        //getValue


    }


    public static com.example.administrator.mfunds.getFundValues getInstance (){

        if(instance == null){

            instance = new com.example.administrator.mfunds.getFundValues();

        }

        return instance;
    }



}



class isTrue {

    private boolean valid;

    private static isTrue instance=null;

    boolean getValue(){

        return valid;
    }

    void setValue(boolean f){

        this.valid = f;
    }

    public static isTrue getInstance (){

        if(instance == null){

            instance = new isTrue();

        }

        return instance;
    }

}
