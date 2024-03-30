package com.example.project_magazine;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SportsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sports_page);

        LinearLayout goToBackToHomeHandler = (LinearLayout) findViewById(R.id.goBackToHome);
        goToBackToHomeHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SportsPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView IV = (ImageView) findViewById(R.id.test_img);
        ECO_ECO_DB DatabaseObject = new ECO_ECO_DB(getApplicationContext());
        Cursor result = DatabaseObject.getArticles("Sports");
        while (result.moveToNext()) {
            byte[] imageData = result.getBlob(result.getColumnIndexOrThrow("ARTICLE_IMAGE"));

            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

            IV.setImageBitmap(bitmap);
        }
    }
}