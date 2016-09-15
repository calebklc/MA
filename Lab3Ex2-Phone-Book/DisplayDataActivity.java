package com.klcal.lab3ex2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayDataActivity extends AppCompatActivity {

    private int                   layoutId;
    private ArrayList<PhoneDb>    phoneDbs;
    private ArrayAdapter<PhoneDb> phoneDbArrayAdapter;
    private ListView              lvItems;
    private Button                btnReturn;
    private PhoneDbHelper         phoneDbHelper;

    public DisplayDataActivity() {
        layoutId = 0;
        phoneDbs = new ArrayList<>();
        phoneDbHelper = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        retrieveRecords();

        layoutId = android.R.layout.simple_list_item_1;
        lvItems = (ListView) findViewById(R.id.lvItems);
        btnReturn = (Button) findViewById(R.id.btnReturn);
        phoneDbArrayAdapter = new PhoneDbAdapter(this, R.layout.phone_item, phoneDbs);

        lvItems.setAdapter(phoneDbArrayAdapter);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void retrieveRecords() {

        phoneDbHelper = new PhoneDbHelper(getApplicationContext());
        SQLiteDatabase db = phoneDbHelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " +
                                        PhoneDbContact.PhoneDbEntry.TABLE_NAME, null);
            while (cursor.moveToNext()) {
                int    id     = cursor.getInt(cursor.getColumnIndex(PhoneDbContact.PhoneDbEntry._ID));
                String name   = cursor.getString(cursor.getColumnIndex(PhoneDbContact.PhoneDbEntry.COLUMN_NAME_NAME));
                String    mobile = cursor.getString(cursor.getColumnIndex(PhoneDbContact.PhoneDbEntry.COLUMN_NAME_MOBILE));
                String office = cursor.getString(cursor.getColumnIndex(PhoneDbContact.PhoneDbEntry.COLUMN_NAME_OFFICE));
                String email  = cursor.getString(cursor.getColumnIndex(PhoneDbContact.PhoneDbEntry.COLUMN_NAME_EMAIL));

                PhoneDb entry = new PhoneDb(id, name, mobile, office, email);

                phoneDbs.add(entry);
            }
        } catch (SQLiteException e) {
            Toast.makeText(this, "Failed to retreieve DB records", Toast.LENGTH_LONG).show();
        }
        db.close();
    }
}
