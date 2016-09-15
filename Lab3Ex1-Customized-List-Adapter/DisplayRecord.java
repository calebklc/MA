package com.klcal.lab3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayRecord extends AppCompatActivity {

    private int             layoutId;
    private ListView        lvItems;
    private Button          btnReturn;
    private ArrayList<Item> data;
    private ItemAdapter     lvItemAdapter;
    private BookDBHelper    dbHelper;

    public DisplayRecord() {
        layoutId = 0;
        data = new ArrayList<>();
        dbHelper = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_record);

        retrieveRecords();

        layoutId = android.R.layout.simple_list_item_1;
        lvItems = (ListView) findViewById(R.id.lvItems);
        btnReturn = (Button) findViewById(R.id.btnReturn);
        lvItemAdapter = new ItemAdapter(this, R.layout.single_item, data);

        lvItems.setAdapter(lvItemAdapter);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishDisplay(view);
            }
        });
    }

    private void retrieveRecords() {
        dbHelper = new BookDBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " +
                                        BookDBContract.BookDbEntry.TABLE_NAME, null);

            while (cursor.moveToNext()) {
                int    id     = cursor.getInt(cursor.getColumnIndex(BookDBContract.BookDbEntry._ID));
                String isbn   = cursor.getString(cursor.getColumnIndex(BookDBContract.BookDbEntry.COLUMN_NAME_ISBN));
                String title  = cursor.getString(cursor.getColumnIndex(BookDBContract.BookDbEntry.COLUMN_NAME_TITLE));
                String author = cursor.getString(cursor.getColumnIndex(BookDBContract.BookDbEntry.COLUMN_NAME_AUTHOR));

                Item entry = new Item(id, isbn, title, author);

                data.add(entry);
            }
        } catch (SQLiteException e) {
            Toast.makeText(this, "Failed to retreieve DB records", Toast.LENGTH_LONG).show();
        }

        db.close();
    }

    private void finishDisplay(View view) {
        finish();
    }
}
