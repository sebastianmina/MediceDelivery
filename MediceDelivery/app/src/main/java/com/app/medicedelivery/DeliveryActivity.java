package com.app.medicedelivery;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DeliveryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<UserDelivery> userDeliveryArrayList;
    UserDeliveryAdapter userDeliveryAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    Button goBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delivery);

        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(v -> startActivity(new Intent(DeliveryActivity.this,
                MainActivity.class)));

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data");
        progressDialog.show();

        recyclerView = findViewById(R.id.viewFirestoreData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        userDeliveryArrayList = new ArrayList<UserDelivery>();
        userDeliveryAdapter = new UserDeliveryAdapter(DeliveryActivity.this,userDeliveryArrayList);

        recyclerView.setAdapter(userDeliveryAdapter);

        EventChangeListener();

    }

    private void EventChangeListener() {

        db.collection("deliveries").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Log.e("Firestore error", error.getMessage());
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()) {

                    if (dc.getType() == DocumentChange.Type.ADDED) {

                        userDeliveryArrayList.add(dc.getDocument().toObject(UserDelivery.class));
                    }

                    userDeliveryAdapter.notifyDataSetChanged();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                }
            }
        });
    }
}