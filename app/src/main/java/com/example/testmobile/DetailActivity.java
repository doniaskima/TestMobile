package com.example.testmobile;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class DetailActivity extends Activity {
    TextView nameView, phoneView;
    ImageButton callButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameView = findViewById(R.id.nameView);
        phoneView = findViewById(R.id.phoneView);
        callButton = findViewById(R.id.callButton);

        String name = getIntent().getStringExtra("NAME");
        final String phone = getIntent().getStringExtra("PHONE");

        nameView.setText(name);
        phoneView.setText(phone);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);
            }
        });
    }
}
