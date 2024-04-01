package com.example.project_magazine;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AuthPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_auth_page);

        LinearLayout goToBackToHomeHandler = (LinearLayout) findViewById(R.id.goBackToHome);
        goToBackToHomeHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ECO_ECO_DB DatabaseObject = new ECO_ECO_DB(getApplicationContext());
                EditText userEmailEditText = (EditText) findViewById(R.id.userAuthEmail);
                EditText userPasswordEditText = (EditText) findViewById(R.id.userAuthPassword);
                String userEmail = userEmailEditText.getText().toString();
                String userPassword = userPasswordEditText.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

                if (!userEmail.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "Invalid Email Format", Toast.LENGTH_LONG).show();
                    return;
                }

                if (userPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                Cursor result = DatabaseObject.checkIfUserExists();
                boolean isAuth = false;
                String dbEmail = "";
                String dbPassword = "";

                while (result.moveToNext()) {
                    dbEmail = result.getString(result.getColumnIndex("email"));
                    dbPassword = result.getString(result.getColumnIndex("password"));

                    if (dbEmail.equals(userEmail) && dbPassword.equals(userPassword)) {
                        isAuth = true;
                        Intent sendToProfile = new Intent(AuthPage.this, ProfilePage.class);
                        sendToProfile.putExtra("username", dbEmail);
                        sendToProfile.putExtra("password", dbPassword);
                        startActivity(sendToProfile);
                        break;
                    }
                }

                result.close();

                if (!isAuth) {
                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                }

            }
        });

        LinearLayout goToSignup = (LinearLayout) findViewById(R.id.goToSignUp);
        goToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthPage.this, SignupPage.class);
                startActivity(intent);
            }
        });
    }
}