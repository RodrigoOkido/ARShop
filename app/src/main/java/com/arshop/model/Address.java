package com.arshop.model;


/**
 * Address class. Class responsible to create an Address associated to the user. Any user can have
 * one or more addressName to receive your purchased products (home, workplace, ...).
 */
public class Address {

    // Private attributes for basic information about the addressName.
    private String addressName, neighborhood, CEP, address_complement;
    private int address_number;


    /**
     * Address constructor.
     *
     * @param addressName Address name.
     * @param neighborhood Address neighborhood.
     * @param CEP Address CEP.
     * @param address_complement Address complement. (Block x, sector y, etc...).
     * @param address_number Address number.
     */
    public Address(String addressName, String neighborhood, String CEP, String address_complement,
                   int address_number){
        this.addressName = addressName;
        this.neighborhood = neighborhood;
        this.CEP = CEP;
        this.address_complement = address_complement;
        this.address_number = address_number;
    }


    // Address GETTERS functions.
    public String getAddressName() {
        return addressName;
    }


    public String getNeighborhood() {
        return neighborhood;
    }


    public String getCEP() {
        return CEP;
    }


    public String getAddress_complement() {
        return address_complement;
    }


    public int getAddress_number() {
        return address_number;
    }



    // Address SETTERS functions.
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }


    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }


    public void setCEP(String CEP) {
        this.CEP = CEP;
    }


    public void setAddress_complement(String address_complement) {
        this.address_complement = address_complement;
    }


    public void setAddress_number(int address_number) {
        this.address_number = address_number;
    }
}
