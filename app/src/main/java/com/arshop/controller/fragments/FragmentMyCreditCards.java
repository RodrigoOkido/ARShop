package com.arshop.controller.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arshop.controller.activities.LoggedUser;
import com.arshop.controller.R;
import com.arshop.model.CreditCard;
import com.arshop.support.dialogs.DialogCreditCardAdder;
import com.arshop.support.recyclers.RecyclerMyCreditCardsView;

import java.util.List;


/**
 * FragmentMyCreditCards. This is an fragment class responsible to show all users credit cards
 * saved to the system. This can be accessed through user profile settings.
 */
public class FragmentMyCreditCards extends Fragment implements DialogCreditCardAdder.DialogListener {


    // Variables which deals with the users credit card information.
    private List<CreditCard> creditCardList;
    private View creditCardFragmentView;

    // Variables to deal with the layout elements.
    private TextView creditCardEmptyText;
    private Button addNewCarcButton;


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

        addNewCarcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openAddCreditCardDialog();
            }
        });

        if (creditCardList.size() != 0) {
            // Remove the text about the empty cart.
            creditCardEmptyText.setText("");

            showCreditCards();
        }

        return creditCardFragmentView;

    }


    /**
     * Creates and open dialog to create a new user credit card.
     */
    public void openAddCreditCardDialog() {
        // Due to being a fragment, it needs to set the target of the dialog to the
        // wanted fragment. In this case, is this fragment.
        DialogCreditCardAdder newAddress = new DialogCreditCardAdder();
        newAddress.setTargetFragment(FragmentMyCreditCards.this,1);
        newAddress.show(getFragmentManager(),"Novo cartão");
    }


    /**
     * Show the user credit cards list.
     */
    public void showCreditCards() {
        // Create the Recycler of the cart view
        RecyclerView creditCardView =
                (RecyclerView) creditCardFragmentView.findViewById(R.id.recycler_my_credit_cards);
        RecyclerMyCreditCardsView creditCardAdapter = new RecyclerMyCreditCardsView(getActivity(), creditCardList);
        creditCardView.setLayoutManager(new LinearLayoutManager(getActivity()));
        creditCardView.setAdapter(creditCardAdapter);

    }


    @Override
    public void addNewCreditCard(String newTitularName, String newCardNumber, String newCardExpirationDate,
                                 int newCardCvv) {

        CreditCard newCard = new CreditCard(CreditCard.Credit.MASTERCARD, newTitularName,
                newCardNumber, newCardExpirationDate, newCardCvv, "", "" );
        creditCardList.add(newCard);
    }


    /**
     * Associates each element of the Layout (from the xml) to a variable.
     */
    public void getLayoutElements(){
        creditCardEmptyText = creditCardFragmentView.findViewById(R.id.creditCardEmptyText);
        addNewCarcButton = creditCardFragmentView.findViewById(R.id.addCreditCardButton);
    }


}
