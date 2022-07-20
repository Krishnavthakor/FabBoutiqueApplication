package com.example.fabboutique.Fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fabboutique.Adapters.ProductCategoryListAdapter;
import com.example.fabboutique.Adapters.ProductListAdapter;
import com.example.fabboutique.DataBaseHandler.DataBaseHandler;
import com.example.fabboutique.Models.ProductCategory;
import com.example.fabboutique.Models.Products;
import com.example.fabboutique.R;

import java.util.ArrayList;
import java.util.List;


public class AllProductFragment extends Fragment {

    private RecyclerView mrecyclerview;
    private List<Products> mList=new ArrayList<>();
    private ProductListAdapter productListAdapter;
    DataBaseHandler dbh;
    String categoryStr;
    private ArrayList<Products> productsList;

    public AllProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_product, container, false);

        //fetching arguements data from previous fragment
        Bundle bundle = this.getArguments();

        if(bundle != null){
            categoryStr = (String) bundle.get("category");
        }

        // here we have created new array list and added data to it.
       /* topsArrayList = new ArrayList<>();
        topsArrayList.add(new ProductCategory("Tops","T-shirts","", R.drawable.casualt));
        topsArrayList.add(new ProductCategory("Tops","Casual Shirts","",R.drawable.casualt));
        topsArrayList.add(new ProductCategory("Tops","Formal Shirts","", R.drawable.shirts));
        topsArrayList.add(new ProductCategory("Tops","Traditional Kurtas","",R.drawable.kurta));
*/
        //binding widgets and initializing objects
        mrecyclerview = view.findViewById(R.id.recyclerView);

        //cursor for fetching data from database
        Cursor cursor = dbh.getProducts(categoryStr);
        if (cursor == null) {
            Toast.makeText(getActivity(), "No Record Found", Toast.LENGTH_LONG).show();
            return view;
        } else {
            Toast.makeText(getActivity(), "Record Found", Toast.LENGTH_LONG).show();
            //if found data then check in recipe table and display it with recylcer view.
            if (cursor.moveToFirst()) {
                do {
                    //initialize recipe class and set data and store object to arraylist
                    Products product = new Products();
                    product.setProductid(cursor.getInt(1));
                    product.setProductName(cursor.getString(2));
                    product.setProductPrice(cursor.getInt(3));
                    product.setProductImage(cursor.getInt(4));
                    mList.add(product);
                } while (cursor.moveToNext());
            }
            cursor.close();
            dbh.close();
            bindAdapter();
            return view;
        }

}

    private void bindAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        // in below lines we are setting layoutmanager and adapter to our recycler view.
        mrecyclerview.setLayoutManager(layoutManager);
        // we are initializing our adapter class and passing our arraylist to it.
        productListAdapter = new ProductListAdapter( mList,getContext());
        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        mrecyclerview.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
    }
    }