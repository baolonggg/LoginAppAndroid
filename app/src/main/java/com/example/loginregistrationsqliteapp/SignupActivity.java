package com.example.loginregistrationsqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.loginregistrationsqliteapp.databinding.ActivitySignUpBinding;

public class SignupActivity extends AppCompatActivity {

    // Binding object for activity_sign_up.xml layout
    ActivitySignUpBinding binding;

    // MySQLConnector instance for database operations
    MySQLConnector mySQLConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using View Binding
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize MySQLConnector
        mySQLConnector = new MySQLConnector();

        // Set up the signup button click listener
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve user input from the signup form
                String email = binding.signupEmail.getText().toString();
                String name = binding.signupUsername.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                // Check if any of the fields are empty
                if(email.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    // Show a message if any field is empty
                    Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the passwords match
                    if(password.equals(confirmPassword)){
                        // Verify if the user email already exists in the database
                        Boolean checkUserExist= mySQLConnector.checkUser(name,password);
                        if(!checkUserExist){
                            // Create a new User object
                            User newUser = new User();
                            newUser.setName(name);
                            newUser.setEmail(email);
                            newUser.setPassword(password);

                            // Add the new user to the database
                            mySQLConnector.addUser(newUser);
                            Toast.makeText(SignupActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();

                            // Navigate to LoginActivity
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            // Show a message if the user already exists
                            Toast.makeText(SignupActivity.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Show a message if the passwords do not match
                        Toast.makeText(SignupActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set up the login redirect text click listener
        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to LoginActivity when the text is clicked
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
