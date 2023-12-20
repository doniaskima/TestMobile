package com.example.testmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(Context context, List<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }

        TextView contactName = (TextView) convertView.findViewById(R.id.contactName);
        TextView contactPhone = (TextView) convertView.findViewById(R.id.contactPhone);

        contactName.setText(contact.getName());
        contactPhone.setText(contact.getPhone());

        return convertView;
    }

    public void updateContacts(List<Contact> contacts) {
        clear();
        addAll(contacts);
        notifyDataSetChanged();
    }
}
