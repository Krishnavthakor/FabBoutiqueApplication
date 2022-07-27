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
import com.example.fabboutique.Models.Products;
import com.example.fabboutique.R;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>{

    private List<Products> productArrayList;
    public ProductListAdapter(List<Products> list, Context context) {
        super();
        productArrayList = list;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName,productPrice;
        ImageView productImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout,parent,false);
        ProductListAdapter.ViewHolder viewHolder=new ProductListAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        Products product = productArrayList.get(position);
        holder.productName.setText(product.getProductName());
       // holder.productImage.setImageResource(product.getProductImage());
        holder.productPrice.setText(product.getProductDesc());
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }
}
