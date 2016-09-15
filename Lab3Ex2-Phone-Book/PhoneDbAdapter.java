package com.klcal.lab3ex2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneDbAdapter extends ArrayAdapter<PhoneDb> {

    private int resourceId;

    public PhoneDbAdapter(Context context, int resource, ArrayList<PhoneDb> phoneDbs) {
        super(context, resource, phoneDbs);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout phoneView;
        PhoneDb      phoneDb = getItem(position);

        if (convertView == null) {
            phoneView = new LinearLayout(getContext());
            String         inflater       = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(inflater);

            layoutInflater.inflate(resourceId, phoneView, true);

        } else {
            phoneView = (LinearLayout) convertView;
        }

        TextView tvName   = (TextView) phoneView.findViewById(R.id.tvName);
        TextView tvMobile = (TextView) phoneView.findViewById(R.id.tvMobile);
        TextView tvOffice = (TextView) phoneView.findViewById(R.id.tvOffice);
        TextView tvEmail  = (TextView) phoneView.findViewById(R.id.tvEmail);

        tvName.setText(phoneDb.get_name());
        tvMobile.setText(phoneDb.get_mobile());
        tvOffice.setText(phoneDb.get_office());
        tvEmail.setText(phoneDb.get_email());

        return phoneView;
    }
}
