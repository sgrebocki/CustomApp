package com.example.customapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.customapp.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnLogin.setOnClickListener(view ->  {
                String email = binding.etLoginEmail.getText().toString();
                String password = binding.etLoginPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(LoginActivity.this, "Pola nie mogą być puste!", Toast.LENGTH_SHORT).show();
                }
        });

        binding.tvForgotPassword.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            view = getLayoutInflater().inflate(R.layout.dialog_forgotpassword, null);
            EditText userEmail = view.findViewById(R.id.etForgotBox);
            builder.setView(view);

            AlertDialog dialog = builder.create();
            view.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    compareEmail(userEmail);
                    dialog.dismiss();
                }
            });

            view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            dialog.show();
        });

        binding.tvRedirectToSignup.setOnClickListener(view -> {
            Intent redirectToSignupIntent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(redirectToSignupIntent);
        });

    }

    private void compareEmail(EditText email) {
        if (email.getText().toString().isEmpty()) {
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            return;
        }
        firebaseAuth.sendPasswordResetEmail(email.getText().toString())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Sprawdź skrzynkę email", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}