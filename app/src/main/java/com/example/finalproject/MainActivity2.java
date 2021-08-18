package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView text,text2;
    Button button,view,home;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.textView);
        text2 = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button1);
        view =(Button) findViewById(R.id.button2);
        home=(Button)findViewById(R.id.home);

        DB = new DBHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), profile.class);
                startActivity(intent);
            }

        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }

        });
        view.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Cursor res = DB.getdata();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity2.this, "Nothing existed!", Toast.LENGTH_SHORT).show();

                }
                else {

                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Name: "+res.getString(0)+"\n");
                        buffer.append("Content: "+res.getString(1)+"\n");
                        buffer.append("Date of Birth: "+res.getString(2)+"\n\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setCancelable(true);
                    builder.setTitle("User");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });

    }
}