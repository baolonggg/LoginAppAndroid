package com.example.loginregistrationsqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.loginregistrationsqliteapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    // Binding object for activity_login.xml layout
    ActivityLoginBinding binding;

    // MySQLConnector instance for database operations
    MySQLConnector mySQLConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using View Binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize MySQLConnector
        mySQLConnector = new MySQLConnector();

        // Set up the login button click listener
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve user input from the login form
                String username = binding.loginUsername.getText().toString();
                String password = binding.loginPassword.getText().toString();

                // Check if username or password is empty
                if(username.isEmpty() || password.isEmpty()) {
                    // Show a message if any field is empty
                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    // Validate credentials with the database
                    Boolean checkCredentials = mySQLConnector.checkUser(username, password);

                    if(checkCredentials) {
                        // Show success message and navigate to MainActivity if credentials are correct
                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        // Show error message if credentials are invalid
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set up the signup redirect text click listener
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to SignupActivity when the text is clicked
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
