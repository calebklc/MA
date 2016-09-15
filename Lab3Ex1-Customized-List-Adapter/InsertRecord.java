package com.klcal.lab3;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertRecord extends AppCompatActivity {

    private BookDBHelper dbHelper = null;

    private EditText etIsbn, etTitle, etAuthor;
    private Button btnInsert, btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_record);

        etIsbn = (EditText) findViewById(R.id.etIsbn);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etAuthor = (EditText) findViewById(R.id.etAuthor);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnFinish = (Button) findViewById(R.id.btnFinish);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRecord(view);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void insertRecord(View view) {

        dbHelper = new BookDBHelper(getApplicationContext());

        SQLiteDatabase db     = dbHelper.getWritableDatabase();
        ContentValues  values = new ContentValues();

        values.put(BookDBContract.BookDbEntry.COLUMN_NAME_ISBN, etIsbn.getText().toString().trim());
        values.put(BookDBContract.BookDbEntry.COLUMN_NAME_TITLE, etTitle.getText().toString().trim());
        values.put(BookDBContract.BookDbEntry.COLUMN_NAME_AUTHOR, etAuthor.getText().toString().trim());

        db.insert("TBL_BOOK", null, values);

        clearForm();
        finishInsert();

        db.close();
    }

    private void finishInsert() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(InsertRecord.this);

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
        etIsbn.setText("");
        etTitle.setText("");
        etAuthor.setText("");
    }
}
