package com.klcal.lab3;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

class ItemAdapter extends ArrayAdapter<Item> {

    private int             resource;
    private ArrayList<Item> items;

    ItemAdapter(Context context, @LayoutRes int resource, ArrayList<Item> items) {
        super(context, resource, items);
        this.resource = resource;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout itemView;
        Item         item = getItem(position);

        if (convertView == null) {
            itemView = new LinearLayout(getContext());
            String         inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li       = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, itemView, true);
        } else {
            itemView = (LinearLayout) convertView;
        }

        TextView tvIsbn   = (TextView) itemView.findViewById(R.id.tvIsbn);
        TextView tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
        TextView tvTitle  = (TextView) itemView.findViewById(R.id.tvTitle);

        tvIsbn.setText(item.getIsbn());
        tvAuthor.setText(item.getAuthor());
        tvTitle.setText(item.getTitle());

        return itemView;
    }
}
