package com.example.fabboutique.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fabboutique.Models.Customer;
import com.example.fabboutique.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{
    private Context context;
    private List<Customer> customerData;

    //RecyclerView
    public ListAdapter(Context context, List<Customer> customerData) {
        super();
        this.customerData = customerData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listItemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        Customer data = customerData.get(position);
        holder.customerImage.setImageResource(data.getImageId());
        holder.nameTextView.setText(data.getName());
        holder.descTextView.setText(data.getDescription());
    }

    @Override
    public int getItemCount() {
        return customerData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView customerImage;
        public TextView nameTextView;
        public TextView descTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customerImage = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
        }
    }
}
