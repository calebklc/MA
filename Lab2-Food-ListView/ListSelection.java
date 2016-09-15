package com.klcal.lab2orderlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListSelection extends AppCompatActivity {

    private ListView lvItems;
    private String[] items;
    private int[]    picIdList;
    private int[]    indexList;
    private Button   btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_selection);

        picIdList = new int[]{
                R.drawable.soup, R.drawable.salad, R.drawable.codfillet, R.drawable.steak, R.drawable.oxtail, R.drawable.currybeef,
                R.drawable.fruitplatter, R.drawable.cheesecake
        };

        // retrieve data items from Intent
        Intent intent = getIntent();
        int          count  = intent.getIntExtra("Count", 0);

        // create String array items
        items = new String[count];
        indexList = new int[count];

        // retrieve data items from intent and
        // add them to array items.
        for (int i = 0; i < count; i++) {
            items[i] = intent.getStringExtra("Item" + i);
            indexList[i] = Integer.parseInt(intent.getStringExtra("Index" + i));
        }

        // process ListView for displaying data items
        lvItems = (ListView) findViewById(R.id.lvItems);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ListSelection.this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(adapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intentShowPicture = new Intent(ListSelection.this, ShowPicture.class);
                intentShowPicture.putExtra("PictureID", String.valueOf(picIdList[indexList[position]]));
                startActivity(intentShowPicture);
            }
        });

        btnFinish = (Button) findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity(view);
            }
        });
    }

    private void finishActivity(View view) {
        finish();
    }
}
