package com.klcal.lab2orderlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ShowPicture extends AppCompatActivity {

    private ImageView ivFood;
    private Button    btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);

        ivFood = (ImageView) findViewById(R.id.ivFood);
        btnReturn = (Button) findViewById(R.id.btnReturn);

        final Intent intent    = getIntent();
        String       pictureID = intent.getStringExtra("PictureID");
        ivFood.setImageResource(Integer.parseInt(pictureID));

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
