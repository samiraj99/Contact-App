package com.ryzen.contacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ViewContact extends AppCompatActivity {

    private TextView fname,lname,email,phoneNo;
    private String mFileName;
    private Contact mLoadContact = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        fname=findViewById(R.id.FirstName);
        lname=findViewById(R.id.LastName);
        email=findViewById(R.id.EmailId);
        phoneNo=findViewById(R.id.PhoneNo);


        mFileName=getIntent().getStringExtra(Utilities.EXTRAS_NOTE_FILENAME);
        if (mFileName!=null&&!mFileName.isEmpty() && mFileName.endsWith(Utilities.FILE_EXTENSION)){

            mLoadContact=Utilities.getContactByFileName(getApplicationContext(),mFileName);
            if(mLoadContact!=null)
            {
                fname.setText(mLoadContact.getFirstName());
                lname.setText(mLoadContact.getLastName());
                email.setText(mLoadContact.getEmail());
                phoneNo.setText(mLoadContact.getPhNo());
            }
        }

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

