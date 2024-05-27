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

/**
 * This class has the functionallity to show the information of Firebase Firestore
 * in the RecyclerView
 */
public class UserDeliveryAdapter extends RecyclerView.Adapter<UserDeliveryAdapter.UserDeliveryViewHolder> {

    Context context;
    ArrayList<UserDelivery> userDeliveryArrayList;

    /**
     * Constructor
     * @param context
     * @param userDeliveryArrayList
     */
    public UserDeliveryAdapter(Context context, ArrayList<UserDelivery> userDeliveryArrayList) {
        this.context = context;
        this.userDeliveryArrayList = userDeliveryArrayList;
    }

    /**
     * Creates the view
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public UserDeliveryAdapter.UserDeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
        return new UserDeliveryViewHolder(v);
    }

    /**
     * Set the information os the view
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull UserDeliveryAdapter.UserDeliveryViewHolder holder, int position) {

        UserDelivery user = userDeliveryArrayList.get(position);
        holder.name.setText(user.name);
        holder.email.setText(user.email);
        holder.address.setText(user.address);
        holder.productName.setText(user.productName);

    }

    /**
     * Returns an arraylist size.
     * @return userDeliveryArrayList
     */
    @Override
    public int getItemCount() {
        return userDeliveryArrayList.size();
    }

    /**
     * This class set the Text Views with the field in DeliveryActivity
     */
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
