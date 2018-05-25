package com.example.watch.serverconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText addBox;
    private Button addButt;
    private ListView dataView;
    private Connect connect;
    private List<String>items;
    private ArrayAdapter<String>adt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstances();
        update();
     addButt.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             connect.sentData(addBox.getText().toString());
             items.add(addBox.getText().toString());
             adt.notifyDataSetChanged();
         }
     });


    }

    private void update() {
        items = connect.getData();
        adt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        dataView.setAdapter(adt);
    }

    private void initInstances() {
       addBox = findViewById(R.id.addBox);
       addButt = findViewById(R.id.addButt);
       dataView = findViewById(R.id.dataView);
       connect = new Connect(MainActivity.this);
    }

}
