package com.example.administrator.mfunds;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import java.util.List;


public class FundsActivity extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_funds);
     //   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);

     //   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
     //   fab.setOnClickListener(new View.OnClickListener() {
     //       @Override
     //       public void onClick(View view) {
     //           Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
     //                   .setAction("Action", null).show();
   //        }
      //  });

     //   MySQLiteHelper db = new MySQLiteHelper(getActivity());
    }


    public FundsActivity() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_funds, container, false);

        ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.layout);
        layout.setBackgroundColor(getArguments().getInt("color"));

        TextView txtdata = (TextView) view.findViewById(R.id.txtfundsdata);
        String contents = getArguments().getString("content");
        txtdata.setText(contents);

          return view;

    }

    public static FundsActivity newInstance(String content, int color){
        FundsActivity home = new FundsActivity();
        Bundle args = new Bundle();
        args.putString("content", content);
        args.putInt("color", color);
        home.setArguments(args);

        return home;
    }

}
