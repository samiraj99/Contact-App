package com.ryzen.contacts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
            ImageView icon =convertView.findViewById(R.id.imageView);
            Fname.setText(contact.getFirstName());
            String i;
            i=contact.getFirstName().substring(0,1).toLowerCase();
            if (i.equals("a"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_a);
            }else if(i.equals("b"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_b);
            }else if(i.equals("c"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_c);
            }else if(i.equals("d"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_d);
            }else if(i.equals("e"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_e);
            }else if(i.equals("f"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_f);
            }else if(i.equals("g"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_g);
            }else if(i.equals("h"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_h);
            }else if(i.equals("i"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_i);
            }else if(i.equals("j"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_j);
            }else if(i.equals("k"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_k);
            }else if(i.equals("l"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_l);
            }else if(i.equals("m"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_m);
            }else if(i.equals("n"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_n);
            }else if(i.equals("o"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_o);
            }else if(i.equals("p"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_p);
            }else if(i.equals("q"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_q);
            }else if(i.equals("r"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_r);
            }else if(i.equals("s"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_s);
            }else if(i.equals("t"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_t);
            }else if(i.equals("u"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_u);
            }else if(i.equals("v"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_v);
            }else if(i.equals("w"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_w);
            }else if(i.equals("x"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_x);
            }else if(i.equals("y"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_y);
            }else if(i.equals("z"))
            {
                icon.setImageResource(R.drawable.ic_contacts__icons_z);
            }

        }
        return convertView;
    }
}
