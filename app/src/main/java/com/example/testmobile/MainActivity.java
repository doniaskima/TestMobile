package com.example.testmobile;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends Activity {
    EditText nameInput, phoneInput;
    ImageButton addButton;
    ListView contactsListView;

    DatabaseHelper databaseHelper;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        phoneInput = findViewById(R.id.phoneInput);
        addButton = findViewById(R.id.addButton);
        contactsListView = findViewById(R.id.contactsListView);

        databaseHelper = new DatabaseHelper(this);
        contactAdapter = new ContactAdapter(this, databaseHelper.getAllContacts());

        contactsListView.setAdapter(contactAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String phone = phoneInput.getText().toString();
                databaseHelper.addContact(new Contact(name, phone));
                contactAdapter.updateContacts(databaseHelper.getAllContacts());
            }
        });

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = (Contact) contactAdapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("NAME", selectedContact.getName());
                intent.putExtra("PHONE", selectedContact.getPhone());
                startActivity(intent);
            }
        });

        contactsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = (Contact) contactAdapter.getItem(position);

                databaseHelper.deleteContact(selectedContact);
                contactAdapter.updateContacts(databaseHelper.getAllContacts());
                return true;
            }
        });
    }
}
