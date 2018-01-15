package com.example.administrator.mfunds;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.method.ScrollingMovementMethod;
import android.widget.LinearLayout;
import android.widget.TextView;





public class ScreenSummaryFragment extends Fragment {


    private Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public ScreenSummaryFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ScreenSummaryFragment newInstance(String param1, String param2) {
        ScreenSummaryFragment fragment = new ScreenSummaryFragment();
    //    Bundle args = new Bundle();
    //    args.putString(ARG_PARAM1, param1);
    //    args.putString(ARG_PARAM2, param2);
    //    fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_screen_summary,
                container, false);



        context = getActivity();



        TextView txtSummary = (TextView) view.findViewById(R.id.txtSummary);
        txtSummary.setMovementMethod(new ScrollingMovementMethod());
        txtSummary.setText(getResources().getString(R.string.FundsSummary));




        // Inflate the layout for this fragment
        return view;





    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);






    }



}
