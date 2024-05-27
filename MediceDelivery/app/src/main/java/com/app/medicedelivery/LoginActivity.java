package com.app.medicedelivery;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.SignInButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * This class has the activity_delivery view
 * and the functionality of the buttons.
 */
public class LoginActivity extends AppCompatActivity {

    TextView btn;
    static EditText inputEmail;
    EditText inputPassword;
    Button btnLogin;
    SignInButton btnGoogle;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate (Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.dontHaveAccount);
        inputEmail = findViewById(R.id.input_Email);
        inputPassword = findViewById(R.id.input_Password);
        btnLogin = findViewById(R.id.buttonLogin);


        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(LoginActivity.this);

        btnLogin.setOnClickListener(v -> checkCredentials());
        btn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this,
                RegisterActivity.class)));

    }

    /**
     * This class verify the conditions that the inputs
     * requires.
     */
    private void checkCredentials() {

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.isEmpty()) {
            showError(inputEmail, "Email is not valid!");
        } else if (!email.contains("@")) {
            showError(inputEmail, "Email is not valid!");
        }
        else if (password.isEmpty() || password.length() < 7){
            showError(inputPassword, "Password must be 7 characters");
        } else {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please wait, while check your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        mLoadingBar.dismiss();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                }
            });
        }
    }

    /**
     * This class show an error in an input.
     * @param input
     * @param s
     */
    private void showError(EditText input, String s) {

        input.setError(s);
        input.requestFocus();
    }

}
