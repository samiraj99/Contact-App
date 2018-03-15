package com.ryzen.contacts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DisplayContact extends AppCompatActivity {
    ListView listView;
    MaterialSearchView searchView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        searchView = findViewById(R.id.search_view);
        listView = findViewById(R.id.List_View);

         fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddCont = new Intent(DisplayContact.this,AddContact.class);
                startActivity(AddCont);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view_item,menu);
        MenuItem item =menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
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


        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                fab.hide();
            }

            @Override
            public void onSearchViewClosed() {
                fab.show();
            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
            return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText!=null && !newText.isEmpty())
                {
                    ArrayList<Contact> fstFound = new ArrayList<>();
                    for (Contact item:contacts){
                        if (item.getFirstName().toLowerCase().contains(newText.toLowerCase()))
                            fstFound.add(item);
                    }
                    ContactAdapter adapter = new ContactAdapter(DisplayContact.this,R.layout.contact_adapter,fstFound);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            String fileName = ((Contact) listView.getItemAtPosition(i)).getFirstName()+Utilities.FILE_EXTENSION;
                            Intent viewCont = new Intent(getApplicationContext(),ViewContact.class);
                            viewCont.putExtra(Utilities.EXTRAS_NOTE_FILENAME,fileName);
                            startActivity(viewCont);
                            searchView.closeSearch();
                        }
                    });

                }
                else{
                    if(contacts !=null && contacts.size() > 0)
                    {
                        final ContactAdapter contactAdapter = new ContactAdapter(DisplayContact.this,R.layout.contact_adapter,contacts);
                        listView.setAdapter(contactAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                String fileName = ((Contact) listView.getItemAtPosition(i)).getFirstName()+Utilities.FILE_EXTENSION;
                                Intent viewCont = new Intent(getApplicationContext(),ViewContact.class);
                                viewCont.putExtra(Utilities.EXTRAS_NOTE_FILENAME,fileName);
                                startActivity(viewCont);
                                searchView.closeSearch();
                            }
                        });

                    }else
                    {
                        Toast.makeText(DisplayContact.this, "You have no saved Contacts !!", Toast.LENGTH_SHORT).show();
                    }
                }
            return true;
            }
        });
        if(contacts !=null && contacts.size() > 0)
        {
            final ContactAdapter contactAdapter = new ContactAdapter(DisplayContact.this,R.layout.contact_adapter,contacts);
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
            Toast.makeText(DisplayContact.this, "You have no saved Contacts !!", Toast.LENGTH_SHORT).show();
        }

    }
}
