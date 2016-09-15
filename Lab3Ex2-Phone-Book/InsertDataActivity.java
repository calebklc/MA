package com.klcal.lab3ex2;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertDataActivity extends AppCompatActivity {

    private PhoneDbHelper dbHelper;
    private Button        btnInsert, btnReturn;
    private EditText etName, etMobile, etOffice, etEmail;

    public InsertDataActivity() {
        dbHelper = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnReturn = (Button) findViewById(R.id.btnReturn);
        etName = (EditText) findViewById(R.id.etName);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etOffice = (EditText) findViewById(R.id.etOffice);
        etEmail = (EditText) findViewById(R.id.etEmail);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData(view);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void insertData(View view) {
        dbHelper = new PhoneDbHelper(getApplicationContext());
        SQLiteDatabase db     = dbHelper.getWritableDatabase();
        ContentValues  values = new ContentValues();

        values.put(PhoneDbContact.PhoneDbEntry.COLUMN_NAME_NAME, etName.getText().toString());
        values.put(PhoneDbContact.PhoneDbEntry.COLUMN_NAME_MOBILE, etMobile.getText().toString());
        values.put(PhoneDbContact.PhoneDbEntry.COLUMN_NAME_OFFICE, etOffice.getText().toString());
        values.put(PhoneDbContact.PhoneDbEntry.COLUMN_NAME_EMAIL, etEmail.getText().toString());

        db.insert(PhoneDbContact.PhoneDbEntry.TABLE_NAME, null, values);

        clearForm();
        finishInsert();

        db.close();
    }

    private void finishInsert() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(InsertDataActivity.this);

        dialog.setTitle("Success");
        dialog.setMessage("Data inserted!");
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog.show();
    }

    private void clearForm() {
        etName.setText("");
        etMobile.setText("");
        etOffice.setText("");
        etEmail.setText("");

    }
}
