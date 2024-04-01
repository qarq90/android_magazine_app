package com.example.project_magazine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddArticlePage extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 100;
    private Uri photoFilePath;
    private Bitmap photoToStore;
    private byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_article_page);

        LinearLayout goToBackToHomeHandler = (LinearLayout) findViewById(R.id.goBackToHome);
        goToBackToHomeHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddArticlePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout chooseImageHandler = (LinearLayout) findViewById(R.id.addImage);
        chooseImageHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST_CODE);
            }
        });

        LinearLayout addArticle = (LinearLayout) findViewById(R.id.addArticle);
        addArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ECO_ECO_DB DatabaseObject = new ECO_ECO_DB(getApplicationContext());
                    EditText articleTitleEditText = findViewById(R.id.articleTitle);
                    EditText articleParaAEditText = findViewById(R.id.articleParaA);
                    EditText articleParaBEditText = findViewById(R.id.articleParaB);
                    Spinner articleTypeSpinner = findViewById(R.id.articleType);
                    String articleTitle = articleTitleEditText.getText().toString();
                    String articleParaA = articleParaAEditText.getText().toString();
                    String articleParaB = articleParaBEditText.getText().toString();
                    String articleType = articleTypeSpinner.getSelectedItem().toString();
                    boolean result = DatabaseObject.insertArticle("qarq90", articleTitle, articleParaA, articleParaB, byteArray, articleType);
                    if (result) {
                        Toast.makeText(getApplicationContext(), "Article Stored", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            photoFilePath = data.getData();
            try {
                photoToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), photoFilePath);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photoToStore.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}