package com.example.fabboutique.Fragments;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fabboutique.Adapters.ProductCategoryListAdapter;
import com.example.fabboutique.DataBaseHandler.DataBaseHandler;
import com.example.fabboutique.Models.ProductCategory;
import com.example.fabboutique.R;

import java.util.ArrayList;
import java.util.List;

public class TopsFragment extends Fragment {

    private RecyclerView mrecyclerview;
    private List<ProductCategory> mList=new ArrayList<>();
    private ProductCategoryListAdapter categoryListAdapter;
    DataBaseHandler dbh;
    private String categoryStr="";
    private ArrayList<ProductCategory> topsArrayList;

    public TopsFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tops, container, false);

        //fetching arguements data from previous fragment
        Bundle bundle = this.getArguments();

        if (bundle != null) {
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

        dbh=new DataBaseHandler(getActivity().getApplicationContext());

        Toast.makeText(getActivity().getApplicationContext(), categoryStr.toString(), Toast.LENGTH_LONG).show();
        //cursor for fetching data from database
        Cursor cursor = dbh.getAllCategory(categoryStr);
        if (cursor == null) {
            Toast.makeText(getActivity(), "No Record Found", Toast.LENGTH_LONG).show();
            return view;
        } else {
            Toast.makeText(getActivity(), "Record Found", Toast.LENGTH_LONG).show();
            //if found data then check in recipe table and display it with recylcer view.
            if (cursor.moveToFirst()) {
                do {
                    //initialize recipe class and set data and store object to arraylist
                    ProductCategory category = new ProductCategory();
                    category.setCategoryName(cursor.getString(2));
                    category.setCategoryTitle(cursor.getString(1));
                    category.setCategoryDescription(cursor.getString(3));
                    //Get Image from db
                    byte[] imageBytes = cursor.getBlob(4);
                    Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes,0, imageBytes.length);
                    category.setCategoryImage(objectBitmap);

                    mList.add(category);
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
        categoryListAdapter = new ProductCategoryListAdapter( mList,getContext());
        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        mrecyclerview.setAdapter(categoryListAdapter);
        categoryListAdapter.notifyDataSetChanged();
    }
}