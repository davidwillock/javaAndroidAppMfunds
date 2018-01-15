package com.example.administrator.mfunds;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ScreenScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_scroll);


        List<Activity> item_act = new ArrayList<>();

        ViewPager viewPager;

        PagerCustomAdapter adapter;




        List<Fragment> items = new ArrayList<>();




        //    Fragment f = FundsActivity.newInstance("Performance fragment", Color.CYAN);
       //    items.add(f);

        ScreenSummaryFragment s = ScreenSummaryFragment.newInstance("Performance fragment", "DATA");
        items.add(s);

        ScreenQuestionFragment q = ScreenQuestionFragment.newInstance("Performance fragment", "DATA");
        items.add(q);




        //Fragment schart = ChartFragSector.newInstance("Sector", "");
        //items.add(schart);


      //    StatsActivity s = StatsActivity.newInstance("Performance fragment", Color.WHITE,"ARGFX");
      //    items.add(s);



        adapter = new PagerCustomAdapter(getSupportFragmentManager(), items);
        viewPager = (ViewPager) findViewById(R.id.container2);
        viewPager.setAdapter(adapter);























    }
}
