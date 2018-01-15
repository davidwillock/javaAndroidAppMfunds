package com.example.administrator.mfunds;

        import android.content.Intent;
        import android.os.Bundle;
        import android.app.ListFragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;



/**
 * Created by Administrator on 24/07/2017.
 */

public class List extends ListFragment{


    Intent i;

    String[] listitems = {
            "ScrollActivity",
            "MainActivity",
            "FundsActivity"
    };
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listitems));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_list_funds,
                container, false);







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










}
