package com.ryzen.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DisplayContact extends AppCompatActivity {
    ListView listView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.List_View);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddCont = new Intent(DisplayContact.this,AddContact.class);
                startActivity(AddCont);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(null);
        final ArrayList<Contact> contacts=Utilities.getAllSavedContact(getApplicationContext());

        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact lhs, Contact rhs) {
                return lhs.getFirstName().compareToIgnoreCase(rhs.getFirstName());
            }
        });
        if(contacts != null &&contacts.size()>0)
        {
            final ContactAdapter contactAdapter = new ContactAdapter(this,R.layout.contact_adapter,contacts);
            listView.setAdapter(contactAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    String fileName = ((Contact) listView.getItemAtPosition(i)).getFirstName()+Utilities.FILE_EXTENSION;
                    Intent viewCont = new Intent(getApplicationContext(),ViewContact.class);
                    viewCont.putExtra(Utilities.EXTRAS_NOTE_FILENAME,fileName);
                    startActivity(viewCont);
                }
            });

        }else
        {
            Toast.makeText(this, "You have no saved Contacts !!", Toast.LENGTH_SHORT).show();
        }
    }
}
