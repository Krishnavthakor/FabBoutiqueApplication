package com.example.fabboutique.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fabboutique.R;

public class HomeFragment extends Fragment {

LinearLayout linearLayoutTops ;
    LinearLayout linearLayoutBottoms ;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        linearLayoutTops=(LinearLayout) view.findViewById(R.id.tops);
        linearLayoutTops.setOnClickListener(this::topsonClick);

        linearLayoutBottoms=(LinearLayout) view.findViewById(R.id.bottoms);
        linearLayoutBottoms.setOnClickListener(this::bottomsOnClick);
        return view;
    }

    public void topsonClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("category","Tops");
        TopsFragment fragment2 = new TopsFragment();
        fragment2.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment2);
        fragmentTransaction.commit();
    }

    public void bottomsOnClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("category","Bottoms");
        TopsFragment fragment2 = new TopsFragment();
        fragment2.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment2);
        fragmentTransaction.commit();
    }
}