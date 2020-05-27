package com.arshop.controller.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arshop.controller.activities.LoggedUser;
import com.arshop.controller.R;
import com.arshop.model.Address;
import com.arshop.support.dialogs.DialogAddressAdder;

import java.util.List;


/**
 * FragmentMyAddress. This is an fragment class responsible to show all the user addresses.
 * This can be accessed through user profile settings.
 */
public class FragmentMyAddress extends Fragment implements DialogAddressAdder.DialogListener {


    // Variables which deals with the user profile information.
    private View myAddressFragmentView;
    private List<Address> addressesList;

    // Variables to deal with the layout elements.
    private EditText addressName, addressNumber, addressComplement, addressCep, addressNeighborhood;
    private EditText secAddressName, secAddressNumber, secAddressComplement, secAddressCep,
        secAddressNeighborhood;
    private TextView alertMessage;

    // Variables to deal button
    private Button addNewAddressButton, addSecondaryAddressButton, cancelSecondaryAddress;


    public FragmentMyAddress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        myAddressFragmentView =
                inflater.inflate(R.layout.fragment_my_address, container, false);

        // Get layout elements.
        getLayoutElements();

        // Load user information data.
        loadUserAddress();

        // Keeps the primary and secondary address fields locked by default.
        lockMainAddressFields();
        lockSecondaryAddressFields();


        addNewAddressButton.setOnClickListener(mListener);
        addSecondaryAddressButton.setOnClickListener(mListener);
        cancelSecondaryAddress.setOnClickListener(mListener);


//        // Put action to secondary address
//        addSecondaryAddressButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        return myAddressFragmentView;
    }

    // Put action to address
    private final View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.editAddressButton:
                    openAddAddressDialog();
                    break;
                case R.id.editSecondaryAddressButton:
                    addSecondaryAddress();
                    cancelSecondaryAddress.setVisibility(View.VISIBLE);
                    break;
                case R.id.cancelSecondaryAddress:
                    cleanAllFields();
                    lockSecondaryAddressFields();
                    addSecondaryAddressButton.setText("Editar");
                    cancelSecondaryAddress.setVisibility(View.INVISIBLE);
                    break;

            }
        }
    };


    /**
     * If user cancel the operation, clean all fields of the secondary address.
     */
    public void cleanAllFields() {
        secAddressName.setText("");
        secAddressNumber.setText("");
        secAddressComplement.setText("");
        secAddressCep.setText("");
        secAddressNeighborhood.setText("");
    }


    /**
     * Creates and open dialog to create a new user address.
     */
    public void openAddAddressDialog() {
        // Due to being a fragment, it needs to set the target of the dialog to the
        // wanted fragment. In this case, is this fragment.
        DialogAddressAdder newAddress = new DialogAddressAdder();
        newAddress.setTargetFragment(FragmentMyAddress.this,1);
        newAddress.show(getFragmentManager(),"Novo endereço");
    }


    /**
     * Adds an secondary address.
     */
    public void addSecondaryAddress() {

        Context context = getActivity();

        if (addSecondaryAddressButton.getText().equals("Editar")) {
            unlockSecondaryAddressFields();
            addSecondaryAddressButton.setText("Salvar");

        } else {
            String secondAddressName = secAddressName.getText().toString();
            int secondAddressNumber = 0;
            if (secAddressNumber.getText().toString().equals("")){
                secondAddressNumber = Integer.parseInt("0");
            } else {
                secondAddressNumber = Integer.parseInt(secAddressNumber.getText().toString());
            }

            String secondAddressComplement = secAddressComplement.getText().toString();
            String secondAddressCep = secAddressCep.getText().toString();
            String secondAddressNeighborhood = secAddressNeighborhood.getText().toString();

            if (secondAddressName.equals("") || secondAddressNumber == 0 ||
                    secondAddressCep.equals("") ||
                    secondAddressNeighborhood.equals("") ) {

                CharSequence text = "Campos com * são obrigatórios!";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

            } else {
                secAddressName.setText(secondAddressName);
                secAddressNumber.setText(String.valueOf(secondAddressNumber));
                if(secondAddressComplement.equals("")){
                    secAddressComplement.setText("Sem Complemento");
                } else {
                    secAddressComplement.setText(secondAddressComplement);
                }
                secAddressCep.setText(secondAddressCep);
                secAddressNeighborhood.setText(secondAddressNeighborhood);

                Address newAddress = new Address(secondAddressName, secondAddressNeighborhood, secondAddressCep,
                        secondAddressComplement, secondAddressNumber);
                addressesList.add(newAddress);

                addSecondaryAddressButton.setText("Editar");
                lockSecondaryAddressFields();

                CharSequence text = "Endereço adicionado!";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * Locks the main address fields.
     */
    private void lockMainAddressFields() {
        addressName.setEnabled(false);
        addressNumber.setEnabled(false);
        addressComplement.setEnabled(false);
        addressCep.setEnabled(false);
        addressNeighborhood.setEnabled(false);
    }


    /**
     * Locks the secondary address fields.
     */
    private void lockSecondaryAddressFields() {
        secAddressName.setEnabled(false);
        secAddressNumber.setEnabled(false);
        secAddressComplement.setEnabled(false);
        secAddressCep.setEnabled(false);
        secAddressNeighborhood.setEnabled(false);
    }


    /**
     * Unlocks the secondary address fields.
     */
    private void unlockSecondaryAddressFields() {
        secAddressName.setEnabled(true);
        secAddressNumber.setEnabled(true);
        secAddressComplement.setEnabled(true);
        secAddressCep.setEnabled(true);
        secAddressNeighborhood.setEnabled(true);
        addSecondaryAddressButton.setEnabled(true);
    }


    /**
     * Overrides addNewAddress from the DialogAddressAdder. Add the new address to the users
     * address list.
     *
     * @param newAddressName The new address name.
     * @param newAddressNumber The new address number.
     * @param newAddressComplement The new address complement.
     * @param newAddressCep The new address cep.
     * @param newAddressNeighborhood The new address neighborhood.
     */
    @Override
    public void addNewAddress(String newAddressName, int newAddressNumber, String newAddressComplement,
                              String newAddressCep, String newAddressNeighborhood) {

        addressName.setText(newAddressName);
        addressNumber.setText(String.valueOf(newAddressNumber));
        addressComplement.setText(newAddressComplement);
        addressCep.setText(newAddressCep);
        addressNeighborhood.setText(newAddressNeighborhood);

        // Add the new address to the user list.
        Address newAddress = new Address (newAddressName, newAddressNeighborhood, newAddressCep,
                newAddressComplement, newAddressNumber);
        addressesList.add(0,newAddress);
    }


    /**
     * Load user address data.
     */
    private void loadUserAddress() {
        addressesList = ((LoggedUser) getActivity().getApplication()).getUser().getAddresses();

        if (addressesList.get(0) != null) {
            addressName.setText(addressesList.get(0).getAddressName());
            addressNumber.setText(String.valueOf(addressesList.get(0).getAddress_number()));
            addressComplement.setText(addressesList.get(0).getAddress_complement());
            addressCep.setText(addressesList.get(0).getCEP());
            addressNeighborhood.setText(addressesList.get(0).getNeighborhood());
        } else if (addressesList.get(1) != null) {
            secAddressName.setText(addressesList.get(1).getAddressName());
            secAddressNumber.setText(String.valueOf(addressesList.get(1).getAddress_number()));
            secAddressComplement.setText(addressesList.get(1).getAddress_complement());
            secAddressCep.setText(addressesList.get(1).getCEP());
            secAddressNeighborhood.setText(addressesList.get(1).getNeighborhood());
        } else {
            alertMessage.setText("Endereço não adicionado. Adicione um endereço para entrega.");
        }
    }


    /**
     * Associates each element of the Layout (from the xml) to a variable.
     */
    private void getLayoutElements() {

        // Main address fields
        addressName = myAddressFragmentView.findViewById(R.id.userProfileAddressName);
        addressNumber = myAddressFragmentView.findViewById(R.id.userProfileAddressNumber);
        addressComplement = myAddressFragmentView.findViewById(R.id.userProfileAddressCompliment);
        addressCep = myAddressFragmentView.findViewById(R.id.userProfileAddressCep);
        addressNeighborhood = myAddressFragmentView.findViewById(R.id.userProfileAddressNeighborhood);
        alertMessage = myAddressFragmentView.findViewById(R.id.alertAddressMsg);
        addNewAddressButton = myAddressFragmentView.findViewById(R.id.editAddressButton);


        // Secondary address fields
        secAddressName = myAddressFragmentView.findViewById(R.id.userProfileSecondaryAddressName);
        secAddressNumber = myAddressFragmentView.findViewById(R.id.userProfileSecondaryAddressNumber);
        secAddressComplement = myAddressFragmentView.findViewById(R.id.userProfileSecondaryAddressCompliment);
        secAddressCep = myAddressFragmentView.findViewById(R.id.userProfileSecondaryAddressCep);
        secAddressNeighborhood = myAddressFragmentView.findViewById(R.id.userProfileSecondaryAddressNeighborhood);
        addSecondaryAddressButton = myAddressFragmentView.findViewById(R.id.editSecondaryAddressButton);
        cancelSecondaryAddress = myAddressFragmentView.findViewById(R.id.cancelSecondaryAddress);


    }

}
