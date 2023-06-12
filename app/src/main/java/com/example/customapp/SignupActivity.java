package com.example.customapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customapp.databinding.ActivitySignupBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnSup.setOnClickListener(view -> {
            String email = binding.etSupEmail.getText().toString();
            String password = binding.etSupPassword.getText().toString();
            String confirmPassword = binding.etSupConfirmPassword.getText().toString();

            if (!email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
                if (password.equals(confirmPassword)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SignupActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(SignupActivity.this, "Hasła różnią się od siebie!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SignupActivity.this, "Pola nie mogą być puste!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.tvRedirectToLogin.setOnClickListener(view -> {
            Intent redirectToLoginIntent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(redirectToLoginIntent);
        });
    }
}