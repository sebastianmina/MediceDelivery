package com.app.medicedelivery;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

public class ScheduleActivity extends AppCompatActivity {
    Button btnGoBack, btnConfirm, btnSaveInfo;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final Map<String, Object> delivery = new HashMap<>();
    private EditText inputName, inputEmail, inputAddress, inputProductName;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule);

        inputName = findViewById(R.id.name);
        inputEmail = findViewById(R.id.email);
        inputAddress = findViewById(R.id.address);
        inputProductName = findViewById(R.id.productName);

        btnGoBack = findViewById(R.id.btnGoBack);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnSaveInfo = findViewById(R.id.btnSaveInfo);

        btnGoBack.setOnClickListener(v -> startActivity(new Intent(ScheduleActivity.this,
                MainActivity.class)));
        btnSaveInfo.setOnClickListener(v -> {
            try {
                checkCredentials();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        btnConfirm.setOnClickListener(v -> startActivity(new Intent(ScheduleActivity.this,
                ConfirmationActivity.class)));


    }

    private void saveInfo(){

        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String address = inputAddress.getText().toString();
        String productName = inputProductName.getText().toString();


        delivery.put("name", name);
        delivery.put("email", email);
        delivery.put("address",address);
        delivery.put("productName",productName);

        UUID document = UUID.randomUUID();


        db.collection("deliveries").document(String.valueOf(document))
                .set(delivery)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Saved");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error saving");
                    }
                });
    }

    private void checkCredentials() throws InterruptedException {

        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String address = inputAddress.getText().toString();
        String productName = inputProductName.getText().toString();

        if (email.isEmpty()) {
            showError(inputEmail, "Email is not valid!");
        } else if (!email.contains("@")) {
            showError(inputEmail, "Email is not valid!");
        }
        else if (name.isEmpty() || name.length() < 2) {
            showError(inputName, "Name is not valid");
        }
        else if (address.isEmpty() || address.length() < 2){
            showError(inputAddress, "Address is not valid");
        }
        else if (productName.isEmpty() || productName.length() < 2){
            showError(inputProductName, "Product Name is not valid");
        }
        else{
            saveInfo();

        }
    }

    private void showError(EditText input, String s) {

        input.setError(s);
        input.requestFocus();
    }

}
