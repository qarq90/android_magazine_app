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
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EntertainmentPage extends AppCompatActivity {

    RelativeLayout EntertainmentMain;
    ScrollView SC;
    LinearLayout articleContainerLayout; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entertainment_page);

        EntertainmentMain = findViewById(R.id.EntertainmentMain);
        articleContainerLayout = findViewById(R.id.articleContainerLayout); 

        LinearLayout goToBackToHomeHandler = findViewById(R.id.goBackToHome);
        goToBackToHomeHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntertainmentPage.this, MainActivity.class);
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
        Cursor result = DatabaseObject.getArticles("Entertainment");

        while (result.moveToNext()) {
            RelativeLayout articleCard = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_article_card, null);
            ImageView articleImage = articleCard.findViewById(R.id.articleImage);
            TextView articleTitle = articleCard.findViewById(R.id.articleTitle);
            TextView articleParaA = articleCard.findViewById(R.id.articleParaA);
            TextView articleParaB = articleCard.findViewById(R.id.articleParaB);
            TextView articleAuthor = articleCard.findViewById(R.id.articleAuthor);

            articleTitle.setText(result.getString(result.getColumnIndexOrThrow("ARTICLE_TITLE")));
            articleParaA.setText(result.getString(result.getColumnIndexOrThrow("ARTICLE_PARA_A")));
            articleParaB.setText(result.getString(result.getColumnIndexOrThrow("ARTICLE_PARA_B")));
            articleAuthor.setText(result.getString(result.getColumnIndexOrThrow("ARTICLE_AUTHOR")));

            byte[] imageData = result.getBlob(result.getColumnIndexOrThrow("ARTICLE_IMAGE"));
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            articleImage.setImageBitmap(bitmap);

            articleContainerLayout.addView(articleCard); 
        }
    }
}
