package com.example.project_magazine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        LinearLayout goToEntertainmentHandler = (LinearLayout) findViewById(R.id.goToEntertainment);
        goToEntertainmentHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EntertainmentPage.class);
                startActivity(intent);
            }
        });

        LinearLayout goToSportsHandler = (LinearLayout) findViewById(R.id.goToSports);
        goToSportsHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SportsPage.class);
                startActivity(intent);
            }
        });

        LinearLayout goToGamesHandler = (LinearLayout) findViewById(R.id.goToGames);
        goToGamesHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GamesPage.class);
                startActivity(intent);
            }
        });

        LinearLayout goToFashionHandler = (LinearLayout) findViewById(R.id.goToFashion);
        goToFashionHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FashionPage.class);
                startActivity(intent);
            }
        });

        LinearLayout goToAddArticleHandler = (LinearLayout) findViewById(R.id.goToAddArticle);
        goToAddArticleHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddArticlePage.class);
                startActivity(intent);
            }
        });

        LinearLayout goToProfileHandler = (LinearLayout) findViewById(R.id.goToProfile);
        goToProfileHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfilePage.class);
                startActivity(intent);
            }
        });

        LinearLayout goToAuthHandler = (LinearLayout) findViewById(R.id.goToAuth);
        goToAuthHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AuthPage.class);
                startActivity(intent);
            }
        });
    }
}