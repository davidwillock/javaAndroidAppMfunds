package com.example.administrator.mfunds;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.*;


public class ListFundsFragment extends ListFragment {

    Context context;



    Intent i;

    String[] listitems = {
            "ScrollActivity",
            "MainActivity",
            "FundsActivity"};






   public ListFundsFragment() {
        // Required empty public constructor
    }


    public static ListFundsFragment newInstance(String param1, String param2) {
        ListFundsFragment fragment = new ListFundsFragment();
        Bundle args = new Bundle();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view =  inflater.inflate(R.layout.fragment_list_funds,
                container, false);


        context = getActivity();


        return view;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id){

        switch(position){
            case 0:
                i = new Intent(getActivity(), ScrollActivity.class);
                break;
            case 1:
                i = new Intent(getActivity(), Main2Activity.class);
                break;
            case 2:
                i = new Intent(getActivity(), FundsActivity.class);
                break;
        }
        startActivity(i);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listitems));


    }
}
