package com.arshop.support.recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.arshop.controller.R;
import com.arshop.model.CreditCard;

import java.util.List;


/**
 * RecyclerMyCreditCardsView class handles the exhibition of user credit card list in profile view.
 */
public class RecyclerMyCreditCardsView
        extends RecyclerView.Adapter<RecyclerMyCreditCardsView.CreditCardView>  {

    private Context context;
    private List<CreditCard> myCredtCards;


    public RecyclerMyCreditCardsView (Context context, List<CreditCard> myCredtCards) {
        this.context = context;
        this.myCredtCards = myCredtCards;
    }


    @NonNull
    @Override
    public RecyclerMyCreditCardsView.CreditCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.card_item_credit_card_view,parent,false);
        return new RecyclerMyCreditCardsView.CreditCardView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMyCreditCardsView.CreditCardView holder, int position) {

        holder.cardUserName.setText(myCredtCards.get(position).getTitularName());
        holder.cardNumber.setText("Termina com "+
                myCredtCards.get(position).getCardNumber().substring(12));
        holder.cardExpiraton.setText(myCredtCards.get(position).getExpirationDate());

        // Once the card was accepted and valid, will be displayed only the basic information
        // of the card.
        holder.cardUserName.setEnabled(false);
        holder.cardNumber.setEnabled(false);
        holder.cardExpiraton.setEnabled(false);
    }


    @Override
    public int getItemCount() {
        return myCredtCards.size();
    }



    public static class CreditCardView extends RecyclerView.ViewHolder {

        private CardView creditCardView;
        private EditText cardUserName;
        private EditText cardNumber;
        private EditText cardExpiraton;


        public CreditCardView(View cardItem){
            super(cardItem);

            creditCardView = (CardView) cardItem.findViewById(R.id.creditCard_item_view);
            cardUserName = (EditText) cardItem.findViewById(R.id.userCreditCardProfileName);
            cardNumber = (EditText) cardItem.findViewById(R.id.userCreditCardProfileNumber);
            cardExpiraton = (EditText) cardItem.findViewById(R.id.userCreditCardProfileExpiringDate);

        }
    }
}


