package com.example.history_chatbot_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private CheckBox checkboxTerms;
    private Button btnSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        checkboxTerms = findViewById(R.id.checkboxTerms);
        btnSignUp = findViewById(R.id.btnSignUp);
        TextView textViewAlreadyAccount = findViewById(R.id.textViewAlreadyAccount);

        textViewAlreadyAccount.setOnClickListener(v -> {
            // Go to Sign In screen
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            finish(); // Optional: prevent returning back here with back button
        });

        mAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!checkboxTerms.isChecked()) {
                Toast.makeText(this, "Please agree to the terms", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, SignInActivity.class)); // Replace with MainActivity if needed
                            finish();
                        } else {
                            Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}
