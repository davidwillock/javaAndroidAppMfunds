package com.example.administrator.mfunds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class RegisterActivity extends AppCompatActivity {


    static boolean isuser;
    static boolean jsonex;
    static boolean isreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText usernameedt = (EditText) findViewById(R.id.editTextUsername);
        final EditText passwordedt = (EditText) findViewById(R.id.editPassword);
        final EditText passwordcfrmedt = (EditText) findViewById(R.id.editTPasswordCfrm);
        final EditText emailedt = (EditText) findViewById(R.id.editTextEmail);
        final EditText countryedt = (EditText) findViewById(R.id.editTextCountry);
        TextView submitbtn = (TextView) findViewById(R.id.textViewBtnSubmit);



        final TextView usernamelbl = (TextView) findViewById(R.id.textViewUserName);
        TextView passwordlbl = (TextView) findViewById(R.id.textViewPassword);
        final TextView passwordcnfrmlbl = (TextView) findViewById(R.id.textViewPasswordCnfrm);
        final TextView emaillbl = (TextView) findViewById(R.id.textViewEmail);

        final String emailConstraint = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";
        final String email = emailedt.getText().toString().trim();




        boolean isEmail= false;
        boolean isPassword=false;



        emailedt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {



                emaillbl.setText("");


            }
        });


        passwordcfrmedt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                passwordcnfrmlbl.setText("");



            }
        });



        usernameedt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                usernamelbl.setText("");


            }
        });



        submitbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String username = usernameedt.getText().toString();
                String pwd = passwordedt.getText().toString();
                String pwdcnfm = passwordcfrmedt.getText().toString();
                String email = emailedt.getText().toString();
                String country = countryedt.getText().toString();

                String func = "checkValidUserResult";

                String url = "http://charlie3.gear.host/MyService.svc/apiIsValidUser/{username:'" + username + "'}";

               // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                //getUserValidation(url);

                isValidUser gotUser = isValidUser.getInstance();

                boolean status = true;

                status = gotUser.getValue();


                if (isuser) {

                    usernamelbl.setText("User already exists!!");

                } else if (!pwd.equals(pwdcnfm) || pwd.length() < 0) {

                    passwordcnfrmlbl.setText("Passwords do not match");


                } else if (!isValidate(email) || email.length() <= 0) {

                    // !email.matches(emailConstraint)
                    emaillbl.setText("Invalid email format");

                } else {


                    if(!jsonex) {
                        String Url = "http://charlie3.gear.host/MyService.svc/apiRegister/{username:'" + username + "',password:'" + pwd + "', email:'" + email + "',country:'" + country + "'}";
                //        Toast.makeText(getApplicationContext(), Url, Toast.LENGTH_SHORT).show();
                        register(Url);

                    }else{

                        jsonex = false;
                    }



                }
            }


        });





    }
    public boolean isValidate(final String hex) {

        Matcher matcher;

        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        final Pattern pattern= Pattern.compile(EMAIL_PATTERN);


        matcher = pattern.matcher(hex);
        return matcher.matches();
    }



    public void register(String url){








        getRegistrationStatus(url);

        isRegistered gotReg = isRegistered.getInstance();

        boolean status = false;

        status = gotReg.getValue();

        if(isreg){

            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();

         //   Intent intentLogin = new Intent(RegisterActivity.this,LoginActivity.class);
         //   startActivity(intentLogin);


        }else{

            Toast.makeText(getApplicationContext(), "Please try again, failure to insert details", Toast.LENGTH_SHORT).show();
        }


    }





    protected void getUserValidation(String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.get(url, params, new TextHttpResponseHandler() {

                    // String valid="";
                    isValidUser getStatus = isValidUser.getInstance();

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"

                        String func = "checkValidUserResult";

                        try {
                            String valid="";
                            JSONObject obj = new JSONObject(res);
                            valid = obj.getString(func);
                            if(valid.equals("true")){

                                isuser = true;

                                getStatus.setValue(true);

                                Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                            }else{

                                getStatus.setValue(false);
                                Toast.makeText(getApplicationContext(), "User doesn't exist", Toast.LENGTH_SHORT).show();
                                isuser = false;

                            }
                            //  Toast.makeText(getApplicationContext(), "Successful connection " , Toast.LENGTH_SHORT).show();



                        } catch (JSONException ex) {

                            getStatus.setValue(true);

                            // isuser= true;
                            Toast.makeText(getApplicationContext(), func + " exception", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();

                            jsonex = true;


                        }


                   //     Toast.makeText(getApplicationContext(), "Successful connection " , Toast.LENGTH_SHORT).show();

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



    protected void getRegistrationStatus(String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.get(url, params, new TextHttpResponseHandler() {

                    // String valid="";
                    isValidUser getStatus = isValidUser.getInstance();

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"

                        String func = "registerResult";

                        try {
                            String valid="";
                            JSONObject obj = new JSONObject(res);
                            valid = obj.getString(func);
                            if(valid.equals("true")){



                                getStatus.setValue(true);
                                isreg =true;
                                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                            }else{

                                getStatus.setValue(false);
                                Toast.makeText(getApplicationContext(), "Registration failure", Toast.LENGTH_SHORT).show();

                                isreg=false;
                            }
                            //  Toast.makeText(getApplicationContext(), "Successful connection " , Toast.LENGTH_SHORT).show();



                        } catch (JSONException ex) {

                            getStatus.setValue(true);

                            // isuser= true;
                            Toast.makeText(getApplicationContext(), func + " exception", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();

                            jsonex = true;


                        }


                        Toast.makeText(getApplicationContext(), "Successful connection " , Toast.LENGTH_SHORT).show();

                        Intent intentLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intentLogin);

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




class isValidUser {

    private boolean valid;

    private static isValidUser instance=null;

    boolean getValue(){

        return valid;
    }

    void setValue(boolean f){

        this.valid = f;
    }

    public static isValidUser getInstance (){

        if(instance == null){

            instance = new isValidUser();

        }

        return instance;
    }



}

class isRegistered {

    private boolean valid;

    private static isRegistered instance=null;

    boolean getValue(){

        return valid;
    }

    void setValue(boolean f){

        this.valid = f;
    }

    public static isRegistered getInstance (){

        if(instance == null){

            instance = new isRegistered();

        }

        return instance;
    }

}
