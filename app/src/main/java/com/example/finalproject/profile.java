package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class profile extends AppCompatActivity {
    EditText name, email, phone;
    Button insert, update, delete,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.Name);
        email = findViewById(R.id.email1);
        phone = findViewById(R.id.phoneno);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NameTxt = name.getText().toString();
                String email1Txt = email.getText().toString();
                String phoneNoTxt = phone.getText().toString();

                Boolean checkinsertData = DB.insertuserdata(NameTxt,email1Txt,phoneNoTxt);

                if(checkinsertData){
                    Toast.makeText(profile.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(profile.this, "New Entry NOT Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTxt = name.getText().toString();
                String contactTxt = email.getText().toString();
                String dobTxt = phone.getText().toString();

                Boolean checkupdate = DB.updateuserdata(nameTxt,contactTxt,dobTxt);

                if(checkupdate){
                    Toast.makeText(profile.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(profile.this, "Entry not Updated", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTxt = name.getText().toString();

                Boolean checkdeletedata = DB.deleteuserdata(nameTxt);

                if(checkdeletedata){
                    Toast.makeText(profile.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(profile.this, "Entry not Deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Cursor res = DB.getdata();
                if(res.getCount() == 0){
                    Toast.makeText(profile.this, "Nothing existed!", Toast.LENGTH_SHORT).show();

                }
                else {

                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Name: "+res.getString(0)+"\n");
                        buffer.append("Content: "+res.getString(1)+"\n");
                        buffer.append("Date of Birth: "+res.getString(2)+"\n\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(profile.this);
                    builder.setCancelable(true);
                    builder.setTitle("Users");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });


    }
}