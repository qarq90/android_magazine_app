package com.example.project_magazine;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FashionPage extends AppCompatActivity {

    RelativeLayout FashionMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fashion_page);

        FashionMain = (RelativeLayout)findViewById(R.id.FashionMain);

        LinearLayout goToBackToHomeHandler = (LinearLayout) findViewById(R.id.goBackToHome);
        goToBackToHomeHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FashionPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        displayArticles();
    }

    public void displayArticles() {
        ECO_ECO_DB DatabaseObject = new ECO_ECO_DB(getApplicationContext());
        Cursor result = DatabaseObject.getArticles("Fashion");

        while (result.moveToNext()) {
            LinearLayout articleCard = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_article_card, null);
            ImageView articleImage = articleCard.findViewById(R.id.articleImage);
            TextView articleTitle = articleCard.findViewById(R.id.articleTitle);
            TextView articleSubPara = articleCard.findViewById(R.id.articleSubPara);
            TextView articleAuthor = articleCard.findViewById(R.id.articleAuthor);

            articleTitle.setText(result.getString(result.getColumnIndexOrThrow("ARTICLE_TITLE")));
            articleSubPara.setText(result.getString(result.getColumnIndexOrThrow("ARTICLE_PARA_A")));
            articleAuthor.setText(result.getString(result.getColumnIndexOrThrow("ARTICLE_AUTHOR")));

            byte[] imageData = result.getBlob(result.getColumnIndexOrThrow("ARTICLE_IMAGE"));
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            articleImage.setImageBitmap(bitmap);

            FashionMain.addView(articleCard);
        }
    }
}