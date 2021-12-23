package com.example.realproject_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Expertise extends AppCompatActivity {
    Button back_list_home;

    public void onBackPressed(){
        Intent intent_information = new Intent(getApplicationContext(), RealHome.class);
        startActivity(intent_information);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expertise);

        back_list_home = (Button)findViewById(R.id.back_list_home);

       final String[] mid ={"초보자를 위한 가이드1화", "초보자를 위한 가이드2화", "초보자를 위한 가이드3화" };

        ListView list = (ListView)findViewById(R.id.Listview_expertise);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mid);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(Expertise.this, Infromation_1.class);
                    startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent(Expertise.this, Information_2.class);
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(Expertise.this, Information_3.class);
                    startActivity(intent);
                }


            }
        });

        back_list_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RealHome.class);
                startActivity(intent);
            }
        });


    }
}
