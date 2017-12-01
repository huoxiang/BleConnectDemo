package com.ajts.androidmads.sqlite2xlDemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajts.androidmads.sqlite2xlDemo.R;
import com.ajts.androidmads.sqlite2xlDemo.model.Users;

import java.util.List;

/**
 * Created by Mushtaq on 12-04-2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ItemViewHolder>  {
    private Context context;
    private List<Users> usersList;

    public CustomAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder viewHolder, final int position) {

        viewHolder.your_first_text_view.setText(usersList.get(position).getContactTime());
        viewHolder.your_second_text_view.setText(usersList.get(position).getContactData());

        viewHolder.zuigaodianya.setText("第"+ usersList.get(position).CONTACT_DYZGJ +"个  " + usersList.get(position).CONTACT_ZGJDY);
        viewHolder.zuididianya.setText("第"+ usersList.get(position).CONTACT_DYZDJ +"个  " + usersList.get(position).CONTACT_ZDJDY);


        if (!"".equals(usersList.get(position).CONTACT_DY.get(0))) {
            viewHolder.l1 .setVisibility(View.VISIBLE);
            viewHolder.danti1 .setText(usersList.get(position).CONTACT_DY.get(0));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(1 ))) {
            viewHolder.l2 .setVisibility(View.VISIBLE);
            viewHolder.danti2 .setText(usersList.get(position).CONTACT_DY.get(1 ));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(2 ))) {
            viewHolder.l3 .setVisibility(View.VISIBLE);
            viewHolder.danti3 .setText(usersList.get(position).CONTACT_DY.get(2 ));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(3 ))) {
            viewHolder.l4 .setVisibility(View.VISIBLE);
            viewHolder.danti4 .setText(usersList.get(position).CONTACT_DY.get(3 ));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(4 ))) {
            viewHolder.l5 .setVisibility(View.VISIBLE);
            viewHolder.danti5 .setText(usersList.get(position).CONTACT_DY.get(4 ));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(5 ))) {
            viewHolder.l6 .setVisibility(View.VISIBLE);
            viewHolder.danti6 .setText(usersList.get(position).CONTACT_DY.get(5 ));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(6 ))) {
            viewHolder.l7 .setVisibility(View.VISIBLE);
            viewHolder.danti7 .setText(usersList.get(position).CONTACT_DY.get(6 ));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(7 ))) {
            viewHolder.l8 .setVisibility(View.VISIBLE);
            viewHolder.danti8 .setText(usersList.get(position).CONTACT_DY.get(7 ));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(8 ))) {
            viewHolder.l9 .setVisibility(View.VISIBLE);
            viewHolder.danti9 .setText(usersList.get(position).CONTACT_DY.get(8 ));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(9 ))) {
            viewHolder.l10.setVisibility(View.VISIBLE);
            viewHolder.danti10.setText(usersList.get(position).CONTACT_DY.get(9 ));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(10))) {
            viewHolder.l11.setVisibility(View.VISIBLE);
            viewHolder.danti11.setText(usersList.get(position).CONTACT_DY.get(10));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(11))) {
            viewHolder.l12.setVisibility(View.VISIBLE);
            viewHolder.danti12.setText(usersList.get(position).CONTACT_DY.get(11));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(12))) {
            viewHolder.l13.setVisibility(View.VISIBLE);
            viewHolder.danti13.setText(usersList.get(position).CONTACT_DY.get(12));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(13))) {
            viewHolder.l14.setVisibility(View.VISIBLE);
            viewHolder.danti14.setText(usersList.get(position).CONTACT_DY.get(13));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(14))) {
            viewHolder.l15.setVisibility(View.VISIBLE);
            viewHolder.danti15.setText(usersList.get(position).CONTACT_DY.get(14));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(15))) {
            viewHolder.l16.setVisibility(View.VISIBLE);
            viewHolder.danti16.setText(usersList.get(position).CONTACT_DY.get(15));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(16))) {
            viewHolder.l17.setVisibility(View.VISIBLE);
            viewHolder.danti17.setText(usersList.get(position).CONTACT_DY.get(16));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(17))) {
            viewHolder.l18.setVisibility(View.VISIBLE);
            viewHolder.danti18.setText(usersList.get(position).CONTACT_DY.get(17));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(18))) {
            viewHolder.l19.setVisibility(View.VISIBLE);
            viewHolder.danti19.setText(usersList.get(position).CONTACT_DY.get(18));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(19))) {
            viewHolder.l20.setVisibility(View.VISIBLE);
            viewHolder.danti20.setText(usersList.get(position).CONTACT_DY.get(19));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(20))) {
            viewHolder.l21.setVisibility(View.VISIBLE);
            viewHolder.danti21.setText(usersList.get(position).CONTACT_DY.get(20));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(21))) {
            viewHolder.l22.setVisibility(View.VISIBLE);
            viewHolder.danti22.setText(usersList.get(position).CONTACT_DY.get(21));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(22))) {
            viewHolder.l23.setVisibility(View.VISIBLE);
            viewHolder.danti23.setText(usersList.get(position).CONTACT_DY.get(22));
        }

        if (!"".equals(usersList.get(position).CONTACT_DY.get(23))) {
            viewHolder.l24.setVisibility(View.VISIBLE);
            viewHolder.danti24.setText(usersList.get(position).CONTACT_DY.get(23));
        }


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView your_first_text_view;
        private TextView your_second_text_view;

        private TextView zuigaodianya;
        private TextView zuididianya;

        private View l1 ;
        private View l2 ;
        private View l3 ;
        private View l4 ;
        private View l5 ;
        private View l6 ;
        private View l7 ;
        private View l8 ;
        private View l9 ;
        private View l10;
        private View l11;
        private View l12;
        private View l13;
        private View l14;
        private View l15;
        private View l16;
        private View l17;
        private View l18;
        private View l19;
        private View l20;
        private View l21;
        private View l22;
        private View l23;
        private View l24;

        private TextView danti1 ;
        private TextView danti2 ;
        private TextView danti3 ;
        private TextView danti4 ;
        private TextView danti5 ;
        private TextView danti6 ;
        private TextView danti7 ;
        private TextView danti8 ;
        private TextView danti9 ;
        private TextView danti10;
        private TextView danti11;
        private TextView danti12;
        private TextView danti13;
        private TextView danti14;
        private TextView danti15;
        private TextView danti16;
        private TextView danti17;
        private TextView danti18;
        private TextView danti19;
        private TextView danti20;
        private TextView danti21;
        private TextView danti22;
        private TextView danti23;
        private TextView danti24;


        public ItemViewHolder(View view) {
            super(view);
            your_first_text_view = (TextView) view.findViewById(R.id.listview_firsttextview);
            your_second_text_view = (TextView) view.findViewById(R.id.listview_secondtextview);
            zuigaodianya = (TextView) view.findViewById(R.id.zuigaodianya);
            zuididianya = (TextView) view.findViewById(R.id.zuididianya);

            l1  = view.findViewById(R.id.l1 );
            l2  = view.findViewById(R.id.l2 );
            l3  = view.findViewById(R.id.l3 );
            l4  = view.findViewById(R.id.l4 );
            l5  = view.findViewById(R.id.l5 );
            l6  = view.findViewById(R.id.l6 );
            l7  = view.findViewById(R.id.l7 );
            l8  = view.findViewById(R.id.l8 );
            l9  = view.findViewById(R.id.l9 );
            l10 = view.findViewById(R.id.l10);
            l11 = view.findViewById(R.id.l11);
            l12 = view.findViewById(R.id.l12);
            l13 = view.findViewById(R.id.l13);
            l14 = view.findViewById(R.id.l14);
            l15 = view.findViewById(R.id.l15);
            l16 = view.findViewById(R.id.l16);
            l17 = view.findViewById(R.id.l17);
            l18 = view.findViewById(R.id.l18);
            l19 = view.findViewById(R.id.l19);
            l20 = view.findViewById(R.id.l20);
            l21 = view.findViewById(R.id.l21);
            l22 = view.findViewById(R.id.l22);
            l23 = view.findViewById(R.id.l23);
            l24 = view.findViewById(R.id.l24);

            danti1  = (TextView) view.findViewById(R.id.danti1 );
            danti2  = (TextView) view.findViewById(R.id.danti2 );
            danti3  = (TextView) view.findViewById(R.id.danti3 );
            danti4  = (TextView) view.findViewById(R.id.danti4 );
            danti5  = (TextView) view.findViewById(R.id.danti5 );
            danti6  = (TextView) view.findViewById(R.id.danti6 );
            danti7  = (TextView) view.findViewById(R.id.danti7 );
            danti8  = (TextView) view.findViewById(R.id.danti8 );
            danti9  = (TextView) view.findViewById(R.id.danti9 );
            danti10 = (TextView) view.findViewById(R.id.danti10);
            danti11 = (TextView) view.findViewById(R.id.danti11);
            danti12 = (TextView) view.findViewById(R.id.danti12);
            danti13 = (TextView) view.findViewById(R.id.danti13);
            danti14 = (TextView) view.findViewById(R.id.danti14);
            danti15 = (TextView) view.findViewById(R.id.danti15);
            danti16 = (TextView) view.findViewById(R.id.danti16);
            danti17 = (TextView) view.findViewById(R.id.danti17);
            danti18 = (TextView) view.findViewById(R.id.danti18);
            danti19 = (TextView) view.findViewById(R.id.danti19);
            danti20 = (TextView) view.findViewById(R.id.danti20);
            danti21 = (TextView) view.findViewById(R.id.danti21);
            danti22 = (TextView) view.findViewById(R.id.danti22);
            danti23 = (TextView) view.findViewById(R.id.danti23);
            danti24 = (TextView) view.findViewById(R.id.danti24);
        }
    }
}
