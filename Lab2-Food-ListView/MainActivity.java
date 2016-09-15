package com.klcal.lab2orderlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    private Button btnConfirm;
    private int[] cbIdList = {
            R.id.cbSoup, R.id.cbSalad, R.id.cbCodFillet, R.id.cbSteak, R.id.cbOxTail, R.id.cbCurryBeef, R.id.cbFruit, R.id.cbCake
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListSelection.class);

                int count = 0;

                for (int i = 0; i < cbIdList.length; i++) {
                    // if a CheckBox has been selected, add its name
                    // to intent
                    CheckBox cb = (CheckBox) findViewById(cbIdList[i]);

                    Log.d("dd", String.valueOf(cb != null ? cb.getId() : 0));

                    if (cb.isChecked()) {
                        intent.putExtra("Item" + count, cb.getText().toString());
                        intent.putExtra("Index" + count, String.valueOf(i));
                        count++;
                    }
                }

                intent.putExtra("Count", count); // total number of items

                startActivity(intent);
            }
        });
    }
}
