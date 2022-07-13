package com.example.fabboutique.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fabboutique.Adapters.ListAdapter;
import com.example.fabboutique.Models.Customer;
import com.example.fabboutique.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerFragement extends Fragment {

    private RecyclerView mrecyclerview;
    private List<Customer> mList=new ArrayList<>();
    private ListAdapter customerListAdapter;
    private ArrayList<Customer> customerArrayList;

    public CustomerFragement()
    {
        /*inserting user to database
        ProductCategory category = new ProductCategory("Tops","T-shirts","T-shirts",1);
        dbh = new DataBaseHandler(getActivity());
        boolean insertState = dbh.insertCategory(category);

        if (insertState) {
            Toast.makeText(getActivity(), "Category Inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Registration Failed!", Toast.LENGTH_LONG).show();
        }*/

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_manage_customers, container, false);

        // here we have created new array list and added data to it.
        customerArrayList = new ArrayList<>();
        customerArrayList.add(new Customer(R.drawable.clothes, "ABC", "Customer-1"));
        customerArrayList.add(new Customer(R.drawable.tops, "XYZ", "Customer-2"));
        customerArrayList.add(new Customer(R.drawable.clothes, "PQR", "Customer-3"));
        customerArrayList.add(new Customer(R.drawable.tops, "EFG", "Customer-4"));

        //binding widgets and initializing objects
        mrecyclerview = view.findViewById(R.id.customerRecyclerView);
/*
        //cursor for fetching data from database
        Cursor cursor=dbh.getAllCategory("Tops");
        if(cursor.getCount()==0) {
            Toast.makeText(getActivity(), "No Record Found", Toast.LENGTH_LONG).show();
        } else {
            //if found data then check in recipe table and display it with recylcer view.
            if(cursor.moveToFirst()) {
                do {
                    Cursor cursor1 = dbh.getAllCategory(cursor.getString(1));
                    if (cursor1.getCount() == 0) {
                        Toast.makeText(getActivity(), "No Record Found", Toast.LENGTH_LONG).show();
                    } else {
                        if (cursor1.moveToFirst()) {
                            //initialize recipe class and set data and store object to arraylist
                            ProductCategory category = new ProductCategory();
                            category.setCategoryName(cursor1.getString(1));
                            category.setCategoryTitle(cursor1.getString(2));
                            category.setCategoryDescription(cursor1.getString(3));
                            category.setCategoryImage(cursor1.getInt(4));
                            mList.add(category);
                        }
                    }
                    cursor1.close();
                } while (cursor.moveToNext());
            }
            cursor.close();
        }


        //initializing our adapter class and passing our arraylist to it.
        categoryListAdapter = new ProductCategoryListAdapter(getActivity(), mList);
        // setting adapter.
        mrecyclerview.setAdapter(categoryListAdapter);
        categoryListAdapter.notifyDataSetChanged();*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        // in below lines we are setting layoutmanager and adapter to our recycler view.
        mrecyclerview.setLayoutManager(layoutManager);
        // we are initializing our adapter class and passing our arraylist to it.
        ListAdapter customerAdapter = new ListAdapter(getContext(), customerArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        mrecyclerview.setLayoutManager(linearLayoutManager);
        mrecyclerview.setAdapter(customerAdapter);
        return view;
    }
}

