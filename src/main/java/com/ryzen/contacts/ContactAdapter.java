package com.ryzen.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by samhacker on 3/2/2018.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(Context context, int resource,List<Contact> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.contact_adapter,null);
        }
        Contact contact=getItem(position);
        if (contact!=null)
        {
            TextView Fname = convertView.findViewById(R.id.list_FName);
            Fname.setText(contact.getFirstName());
        }
        return convertView;
    }
}
