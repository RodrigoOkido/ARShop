package com.arshop.controller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arshop.controller.R;
import com.arshop.controller.activities.LoggedUser;
import com.arshop.model.Product;
import com.arshop.support.recyclers.RecyclerMyPurchaseView;

import java.util.List;

public class FragmentMyPurchases extends Fragment {



    // Variables which deals with the users credit card information.
    private static List<List<Product>> purchasesList;
    private View purchasesFragmentView;

    // Variables to deal with the layout elements.
    private TextView purchasesEmptyText;


    public FragmentMyPurchases() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        purchasesFragmentView =
                inflater.inflate(R.layout.fragment_my_purchases, container, false);

        getLayoutElements();
        purchasesList = ((LoggedUser) getActivity().getApplication()).getUsersPurchases();


        if (purchasesList.size() != 0) {
            // Remove the text about the empty cart.
            purchasesEmptyText.setText("");

            showPurchases();
        }

        return purchasesFragmentView;

    }


    /**
     * Show the user credit cards list.
     */
    public void showPurchases() {
        // Create the Recycler of the cart view
        RecyclerView purchasesView =
                (RecyclerView) purchasesFragmentView.findViewById(R.id.recycler_my_purchases);
        RecyclerMyPurchaseView purchasesAdapter = new RecyclerMyPurchaseView(getActivity(), purchasesList);
        purchasesView.setLayoutManager(new LinearLayoutManager(getActivity()));
        purchasesView.setAdapter(purchasesAdapter);

    }



    /**
     * Associates each element of the Layout (from the xml) to a variable.
     */
    public void getLayoutElements(){
        purchasesEmptyText = purchasesFragmentView.findViewById(R.id.purchasesEmptyText);
//        purchasesDetailButton = purchasesFragmentView.findViewById(R.id.addCreditCardButton);
    }
}
