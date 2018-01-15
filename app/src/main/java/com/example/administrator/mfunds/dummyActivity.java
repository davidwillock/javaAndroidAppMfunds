package com.example.administrator.mfunds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dummyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);


        // helper hack activity to link between activitys and fragments
        Intent i = new Intent(dummyActivity.this,MListActivity.class);
        startActivity(i);


          // Intent i = new Intent(dummyActivity.this,MListActivity.class);
          // startActivity(i);
        /*
        Button btnOk = (Button) findViewById(R.id.btnDummycheckok);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(dummyActivity.this,MListActivity.class);
                startActivity(i);

            }
        });

        */

    }
}
