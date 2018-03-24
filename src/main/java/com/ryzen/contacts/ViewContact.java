package com.ryzen.contacts;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewContact extends AppCompatActivity {

    private TextView fname,lname,email,phoneNo;
    private String mFileName;
    private Contact mLoadContact = null;
    ImageButton call, mail;
    String phno;
    ImageView Dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        fname=findViewById(R.id.FirstName);
        lname=findViewById(R.id.LastName);
        email=findViewById(R.id.EmailId);
        phoneNo=findViewById(R.id.PhoneNo);
        Dp=findViewById(R.id.imageView2);

        mFileName=getIntent().getStringExtra(Utilities.EXTRAS_NOTE_FILENAME);
        if (mFileName!=null&&!mFileName.isEmpty() && mFileName.endsWith(Utilities.FILE_EXTENSION)){

            mLoadContact=Utilities.getContactByFileName(getApplicationContext(),mFileName);
            if(mLoadContact!=null)
            {
                fname.setText(mLoadContact.getFirstName());
                lname.setText(mLoadContact.getLastName());
                email.setText(mLoadContact.getEmail());
                phoneNo.setText(mLoadContact.getPhNo());
                String i=mLoadContact.getFirstName().substring(0,1).toLowerCase();
                if (i.equals("a"))
                {
                 Dp.setImageResource(R.drawable.ic_contacts__icons_a);
                }else if(i.equals("b"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_b);
                }else if(i.equals("c"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_c);
                }else if(i.equals("d"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_d);
                }else if(i.equals("e"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_e);
                }else if(i.equals("f"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_f);
                }else if(i.equals("g"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_g);
                }else if(i.equals("h"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_h);
                }else if(i.equals("i"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_i);
                }else if(i.equals("j"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_j);
                }else if(i.equals("k"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_k);
                }else if(i.equals("l"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_l);
                }else if(i.equals("m"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_m);
                }else if(i.equals("n"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_n);
                }else if(i.equals("o"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_o);
                }else if(i.equals("p"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_p);
                }else if(i.equals("q"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_q);
                }else if(i.equals("r"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_r);
                }else if(i.equals("s"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_s);
                }else if(i.equals("t"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_t);
                }else if(i.equals("u"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_u);
                }else if(i.equals("v"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_v);
                }else if(i.equals("w"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_w);
                }else if(i.equals("x"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_x);
                }else if(i.equals("y"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_y);
                }else if(i.equals("z"))
                {
                    Dp.setImageResource(R.drawable.ic_contacts__icons_z);
                }

            }
        }

        call= findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });

        mail=findViewById(R.id.email);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailit();
            }
        });


    }

    public void makeCall(){
        Intent call=new Intent(Intent.ACTION_CALL);
        phno=phoneNo.getText().toString();
        call.setData(Uri.parse("tel:"+phno));

        if(ActivityCompat.checkSelfPermission(ViewContact.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);
        }else
        {
            startActivity(call);
        }
    }

    public void mailit()
    {

        String E;
        Intent mail = new Intent(Intent.ACTION_SEND);
        E = email.getText().toString();
        String[] mailit ={E};
        mail.setData(Uri.parse("email"));
        mail.putExtra(Intent.EXTRA_EMAIL,mailit);
        mail.setType("message/rfc822");
        Intent chooser = Intent.createChooser(mail,"Launch Email");
        startActivity(chooser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_contact_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_Edit:
                EditContact();
                break;
            case R.id.action_delete:
                ActionDelete();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void EditContact()
    {
        String fileName =  mFileName=getIntent().getStringExtra(Utilities.EXTRAS_NOTE_FILENAME);
        Intent editCont = new Intent(getApplicationContext(),AddContact.class);
        editCont.putExtra(Utilities.EXTRAS_NOTE_FILENAME,fileName);
        startActivity(editCont);
        finish();
    }

    void ActionDelete()
    {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this)
                .setMessage("Delete Contact ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(mLoadContact != null && Utilities.deleteFile(getApplicationContext(),mFileName)){
                            Toast.makeText(ViewContact.this, "Contact is Deleted", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ViewContact.this, "Can not delete the contact", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                })
                .setNegativeButton("Cancel",null);
        dialogDelete.show();
    }
}

