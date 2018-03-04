package com.ryzen.contacts;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    private EditText fName, lName, emailId, pNo;
     private Button save;
    private String mFileName;
    private Contact mLoadContact = null;
    private boolean mIsViewingOrUpdating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        save = findViewById(R.id.Save);
        fName = findViewById(R.id.FirstName);
        lName = findViewById(R.id.LastName);
        emailId = findViewById(R.id.EmailId);
        pNo = findViewById(R.id.PhoneNo);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAndSaveContact();
            }
        });

        mFileName = getIntent().getStringExtra(Utilities.EXTRAS_NOTE_FILENAME);
        if(mFileName!= null && ! mFileName.isEmpty() && mFileName.endsWith(Utilities.FILE_EXTENSION))
        {
            mLoadContact=Utilities.getContactByFileName(getApplicationContext(),mFileName);
            if(mLoadContact != null)
            {
                fName.setText(mLoadContact.getFirstName());
                lName.setText(mLoadContact.getLastName());
                emailId.setText(mLoadContact.getEmail());
                pNo.setText(mLoadContact.getPhNo());
            mIsViewingOrUpdating = true;
            }else {
                mIsViewingOrUpdating = false;
            }
        }
    }
    private void validateAndSaveContact() {

        final String FirstName = fName.getText().toString();
        final String LastName = lName.getText().toString();
        final String Email = emailId.getText().toString();
        final String PhNo = pNo.getText().toString();
        if (FirstName.isEmpty()) { //title
            Toast.makeText(AddContact.this, "please enter a First Name!"
                    , Toast.LENGTH_SHORT).show();
            return;
        }

        if (LastName.isEmpty()) { //content
            Toast.makeText(AddContact.this, "please enter a Last Name!"
                    , Toast.LENGTH_SHORT).show();
            return;
        }
        if (Email.isEmpty()) { //content
            Toast.makeText(AddContact.this, "please enter a Email!"
                    , Toast.LENGTH_SHORT).show();
            return;
        }
        if (PhNo.isEmpty()) { //content
            Toast.makeText(AddContact.this, "please enter a Phone No!"
                    , Toast.LENGTH_SHORT).show();
            return;
        }

        if(!mIsViewingOrUpdating && Utilities.CheckAlreadyExitsFile(this,FirstName)) {
            AlertDialog.Builder warning = new AlertDialog.Builder(this)
                    .setMessage("You already have a contact with that name.You can rename this contact " +
                            "or save it anyway.")
                    .setTitle("Rename Contact?")
                    .setPositiveButton("Rename",null)
                    .setNegativeButton("Save anyway", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Utilities.saveContact(getApplicationContext(),new Contact(FirstName,LastName,Email,PhNo));
                     finish();
                        }
                    });
            warning.show();

        }else if (Utilities.saveContact(this,new Contact(FirstName,LastName,Email,PhNo)))
        {
            Toast.makeText(this, "Contact Saved", Toast.LENGTH_SHORT).show();
            finish();
        } else
        {
            Toast.makeText(this, "Failed to save contact", Toast.LENGTH_SHORT).show();
            finish();
        }

    }


}

