package com.example.project_magazine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_page);

        LinearLayout goToBackToHomeHandler = (LinearLayout) findViewById(R.id.goBackToHome);
        goToBackToHomeHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onResume() {
        super.onResume();
//        Intent intent = getIntent();
//        if (intent != null) {
//            String username = intent.getStringExtra("username");
//            String password = intent.getStringExtra("password");
//            if (username != null && password != null) { // Check if both username and password are not null
//                ECO_ECO_DB DatabaseObject = new ECO_ECO_DB(getApplicationContext());
//                Cursor currentUser = DatabaseObject.fetchUser(username, password);
//                if (currentUser != null && currentUser.moveToFirst()) {
//                    String fetchedUsername = currentUser.getString(currentUser.getColumnIndex("username"));
//                    String fetchedPhoneNumber = currentUser.getString(currentUser.getColumnIndex("phoneNumber"));
//                    String fetchedEmail = currentUser.getString(currentUser.getColumnIndex("email"));
//
//                    EditText editTextUsername = findViewById(R.id.editTextUsername);
//                    EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
//                    EditText editTextEmail = findViewById(R.id.editTextEmail);
//                    EditText editTextPassword = findViewById(R.id.editTextPassword);
//
//                    editTextUsername.setText(fetchedUsername);
//                    editTextPhoneNumber.setText(fetchedPhoneNumber);
//                    editTextEmail.setText(fetchedEmail);
//                    editTextPassword.setText(password); // Assuming you want to display the password for the user
//                }
//                if (currentUser != null) {
//                    currentUser.close();
//                }
//            } else {
//                // Handle the case where username or password is null
//                // For example, display an error message or redirect the user
//            }
        AuthPage AP = new AuthPage();
        String currUser = AP.userEmail;
        String currPass = AP.userPassword;
        Log.d("Current User", currUser + " " + currPass);
    }
}