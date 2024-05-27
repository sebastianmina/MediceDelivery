package com.app.medicedelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class UserDeliveryAdapter extends RecyclerView.Adapter<UserDeliveryAdapter.UserDeliveryViewHolder> {

    Context context;
    ArrayList<UserDelivery> userDeliveryArrayList;

    public UserDeliveryAdapter(Context context, ArrayList<UserDelivery> userDeliveryArrayList) {
        this.context = context;
        this.userDeliveryArrayList = userDeliveryArrayList;
    }

    @NonNull
    @Override
    public UserDeliveryAdapter.UserDeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
        return new UserDeliveryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDeliveryAdapter.UserDeliveryViewHolder holder, int position) {

        UserDelivery user = userDeliveryArrayList.get(position);
        holder.name.setText(user.name);
        holder.email.setText(user.email);
        holder.address.setText(user.address);
        holder.productName.setText(user.productName);

    }

    @Override
    public int getItemCount() {
        return userDeliveryArrayList.size();
    }

    public static class UserDeliveryViewHolder extends RecyclerView.ViewHolder{

        TextView name, email, address, productName;
        public UserDeliveryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cvName);
            email = itemView.findViewById(R.id.cvEmail);
            address = itemView.findViewById(R.id.cvAddress);
            productName = itemView.findViewById(R.id.cvProductName);
        }
    }
}
