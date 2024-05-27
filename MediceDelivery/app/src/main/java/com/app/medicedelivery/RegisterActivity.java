package com.app.medicedelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    TextView btn;
    private EditText inputUsername, inputPassword, inputEmail, inputConfirmPassword;
    Button btnRegister;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    @Override
    protected void onCreate (Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_register);

        btn = findViewById(R.id.alreadyHaveAccount);
        inputUsername = findViewById(R.id.input_Username);
        inputEmail = findViewById(R.id.input_Email);
        inputPassword = findViewById(R.id.input_Password);
        inputConfirmPassword = findViewById(R.id.input_confirmPassword);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(RegisterActivity.this);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> checkCredentials());

        btn.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this,
                LoginActivity.class)));
    }

    private void checkCredentials() {

        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if (username.isEmpty() || username.length() < 7){

            showError(inputUsername, "Your username is not valid!");
        }
        else if (email.isEmpty()) {
            showError(inputEmail, "Email is not valid!");
        } else if (!email.contains("@")) {
            showError(inputEmail, "Email is not valid!");
        } else {
            if (password.isEmpty() || password.length() < 7) {
                showError(inputPassword, "Password must be 7 characters");
            } else if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
                showError(inputConfirmPassword, "Passwords are not equal");
            } else {
                mLoadingBar.setTitle("Registration");
                mLoadingBar.setMessage("Please wait, while check your credentials");
                mLoadingBar.setCanceledOnTouchOutside(false);
                mLoadingBar.show();

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this::onComplete);
            }
        }
    }

    private void showError(EditText input, String s) {

        input.setError(s);
        input.requestFocus();
    }

    private void onComplete(Task<AuthResult> task) {
        if (task.isSuccessful()) {
            Toast.makeText(RegisterActivity.this, "Succesfull", Toast.LENGTH_SHORT).show();

            mLoadingBar.dismiss();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(RegisterActivity.this, Objects.requireNonNull(task.getException()).toString(), Toast.LENGTH_SHORT).show();
        }
    }
}