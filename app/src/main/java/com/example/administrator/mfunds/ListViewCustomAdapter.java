package com.example.administrator.mfunds;

/**
 * Created by Administrator on 07/08/2017.
 */


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

    public class ListViewCustomAdapter extends BaseAdapter
    {
        public String[] symbol;
        public String[] symbolName;
        public double[] navPs;
        public Activity context;
        public LayoutInflater inflater;

        public ListViewCustomAdapter(Activity context,String[] symbol, String[] symbolName,double[] navPs) {
            super();

            this.context = context;
            this.symbol = symbol;
            this.symbolName = symbolName;
            this.navPs = navPs;

            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return symbolName.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        public static class ViewHolder
        {
            ImageView imgViewLogo;
            TextView txtViewSymbol;
            TextView txtViewSymFullName;
            TextView textViewNavPS;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            ViewHolder holder;
            if(convertView==null)
            {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.listoffunds, null);

                holder.imgViewLogo = (ImageView) convertView.findViewById(R.id.imgViewLogo);
                holder.txtViewSymbol = (TextView) convertView.findViewById(R.id.txtViewSymbol);
                holder.txtViewSymFullName = (TextView) convertView.findViewById(R.id.txtViewSymFullName);
                holder.textViewNavPS = (TextView) convertView.findViewById(R.id.textViewNavPS);

                convertView.setTag(holder);
            }
            else
                holder=(ViewHolder)convertView.getTag();

        //    holder.imgViewLogo.setImageResource(R.drawable.icon);
            holder.txtViewSymbol.setText(symbol[position]);
            holder.txtViewSymFullName.setText(symbolName[position]);
            holder.textViewNavPS.setText(String.valueOf(navPs[position]));




            return convertView;
        }

    }


























