package com.arshop.support.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.arshop.controller.R;


/**
 * AddressAdder dialog. This class is responsible to show an dialog window to user add an new
 * address.
 */
public class DialogAddressAdder extends DialogFragment {


    private DialogListener listener;

    private EditText newAddressName, newAddressNumber, newAddressComplement, newAddressCep,
        newAddressNeighborhood;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_new_address, null);

        builder.setView(view)
                .setTitle("Novo endereço")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String addressName = newAddressName.getText().toString();
                        int addressNumber = Integer.valueOf(newAddressNumber.getText().toString());
                        String addressComplement = newAddressComplement.getText().toString();
                        String addressCep = newAddressCep.getText().toString();
                        String addressNeighborhood = newAddressNeighborhood.getText().toString();

                        if (!addressName.equals("") && addressNumber >= 0
                        && !addressCep.equals("") && !addressNeighborhood.equals("")) {

                            if(addressComplement.equals("")){
                                addressComplement = "Sem Complemento";
                            }

                            listener.addNewAddress(addressName, addressNumber, addressComplement,
                                    addressCep, addressNeighborhood);

                            Toast.makeText(getActivity(),"Endereço modificado com sucesso!",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(),"Campos com * são obrigatórios!",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });

        // Get each layout element.
        newAddressName = view.findViewById(R.id.userNewProfileAddressName);
        newAddressNumber = view.findViewById(R.id.userNewProfileAddressNumber);
        newAddressComplement = view.findViewById(R.id.userNewProfileAddressCompliment);
        newAddressCep = view.findViewById(R.id.userNewProfileAddressCep);
        newAddressNeighborhood = view.findViewById(R.id.userNewProfileAddressNeighborhood);

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
        void addNewAddress(String addressName, int addressNumber, String addressComplement,
                           String addressCep, String addressNeighborhood);
    }

}

