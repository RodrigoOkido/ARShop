package com.arshop.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arshop.controller.R;


/**
 * FragmentMyCreditCards. This is an fragment class responsible to show all users credit cards
 * saved to the system. This can be accessed through user profile settings.
 */
public class FragmentMyCreditCards extends Fragment {


    public FragmentMyCreditCards() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_credit_cards, container, false);
    }

}
