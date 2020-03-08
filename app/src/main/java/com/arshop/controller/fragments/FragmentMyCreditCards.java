package com.arshop.controller.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arshop.controller.activities.LoggedUser;
import com.arshop.controller.R;
import com.arshop.model.CreditCard;
import com.arshop.support.recyclers.RecyclerMyCreditCardsView;

import java.util.List;


/**
 * FragmentMyCreditCards. This is an fragment class responsible to show all users credit cards
 * saved to the system. This can be accessed through user profile settings.
 */
public class FragmentMyCreditCards extends Fragment {


    // Variables which deals with the users credit card information.
    private List<CreditCard> creditCardList;
    private View creditCardFragmentView;

    // Variables to deal with the layout elements.
    private TextView creditCardEmptyText;


    public FragmentMyCreditCards() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        creditCardFragmentView =
                inflater.inflate(R.layout.fragment_my_credit_cards, container, false);

        getLayoutElements();
        creditCardList = ((LoggedUser) getActivity().getApplication()).getUser().getUser_cards();

        if (creditCardList.size() != 0) {
            // Remove the text about the empty cart.
            creditCardEmptyText.setText("");

            // Create the Recycler of the cart view
            RecyclerView creditCardView =
                    (RecyclerView) creditCardFragmentView.findViewById(R.id.recycler_my_credit_cards);
            RecyclerMyCreditCardsView creditCardAdapter = new RecyclerMyCreditCardsView(getActivity(), creditCardList);
            creditCardView.setLayoutManager(new LinearLayoutManager(getActivity()));
            creditCardView.setAdapter(creditCardAdapter);
        }

        return creditCardFragmentView;

    }


    /**
     * Associates each element of the Layout (from the xml) to a variable.
     */
    public void getLayoutElements(){
        creditCardEmptyText = creditCardFragmentView.findViewById(R.id.creditCardEmptyText);
    }

}
