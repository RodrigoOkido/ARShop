package com.arshop.support.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.arshop.controller.R;


/**
 * CreditCardAdder dialog. This class is responsible to show an dialog window to user add an new
 * credit card.
 */
public class DialogCreditCardAdder extends DialogFragment {

    // Private attributes to control the dialog elements.
    private DialogCreditCardAdder.DialogListener listener;
    private EditText newTitularName, newCardNumber, newCardExpiringDate, newCardCvv;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_new_credit_card, null);

        builder.setView(view)
                .setTitle("Novo cartão")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String titularName = newTitularName.getText().toString();
                        String cardNumber = newCardNumber.getText().toString();
                        String cardExpirationDate = newCardExpiringDate.getText().toString();
                        String cardCvv = newCardCvv.getText().toString();

                        if (!titularName.equals("") && !cardNumber.equals("")
                                && !cardExpirationDate.equals("") && !cardCvv.equals("")) {

                            listener.addNewCreditCard(titularName, cardNumber, cardExpirationDate,
                                    Integer.valueOf(cardCvv));

                            Toast.makeText(getActivity(),"Cartão adicionado!",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(),"Todos campos são obrigatórios!",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });

        // Get each layout element.
        newTitularName = view.findViewById(R.id.newUserCreditCardName);
        newCardNumber = view.findViewById(R.id.newUserCreditCardNumber);
        newCardExpiringDate = view.findViewById(R.id.newUserCreditCardExpiringDate);
        newCardCvv = view.findViewById(R.id.newUserCreditCardCvv);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            // Attach the listener to the fragment.
            listener = (DialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement DialogListener");
        }
    }

    public interface DialogListener {
        void addNewCreditCard(String newTitularName, String newCardNumber, String newCardExpirationDate,
                           int newCardCvv);
    }
}
