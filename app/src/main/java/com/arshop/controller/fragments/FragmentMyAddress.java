package com.arshop.controller.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private TextView alertMessage;
    private Button addNewAddressButton;


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

        addNewAddressButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAddAddressDialog();
            }
        });

        return myAddressFragmentView;
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
        addressesList.add(newAddress);
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
        } else {
            alertMessage.setText("Endereço não adicionado. Adicione um endereço para entrega.");
        }
    }


    /**
     * Associates each element of the Layout (from the xml) to a variable.
     */
    private void getLayoutElements() {
        addressName = myAddressFragmentView.findViewById(R.id.userProfileAddressName);
        addressNumber = myAddressFragmentView.findViewById(R.id.userProfileAddressNumber);
        addressComplement = myAddressFragmentView.findViewById(R.id.userProfileAddressCompliment);
        addressCep = myAddressFragmentView.findViewById(R.id.userProfileAddressCep);
        addressNeighborhood = myAddressFragmentView.findViewById(R.id.userProfileAddressNeighborhood);
        alertMessage = myAddressFragmentView.findViewById(R.id.alertAddressMsg);
        addNewAddressButton = myAddressFragmentView.findViewById(R.id.addNewAddressButton);



    }

}
