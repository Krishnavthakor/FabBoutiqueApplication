package com.example.fabboutique.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fabboutique.Models.ProductCategory;
import com.example.fabboutique.R;

import java.util.List;

public class ProductCategoryListAdapter extends RecyclerView.Adapter<ProductCategoryListAdapter.ViewHolder>  {

    private List<ProductCategory> categoryArrayList;

    public ProductCategoryListAdapter( List<ProductCategory> list,Context context) {
        super();
        categoryArrayList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryName = itemView.findViewById(R.id.categoryName);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductCategory category = categoryArrayList.get(position);
        holder.categoryName.setText(category.getCategoryName());
        holder.categoryImage.setImageBitmap(category.getCategoryImage());
    }


    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

}
