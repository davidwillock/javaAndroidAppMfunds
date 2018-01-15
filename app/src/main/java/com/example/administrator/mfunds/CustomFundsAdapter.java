package com.example.administrator.mfunds;

/**
 * Created by Administrator on 17/07/2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class CustomFundsAdapter extends ArrayAdapter<Lfund>{


    public CustomFundsAdapter(Context context, ArrayList<Lfund> funds) {

        super(context,0,funds);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_mlist, parent, false);
        }

        Lfund fund = new Lfund();//= getItem(position);


        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
      //  TextView tvHome = (TextView) convertView.findViewById(R.id.tvHometown);
        // Populate the data into the template view using the data object
        tvName.setText(fund.getSymbol());
     //   tvHome.setText(fund.getName());
        // Return the completed view to render on screen
        return convertView;


        }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        super.setNotifyOnChange(notifyOnChange);
    }
}

