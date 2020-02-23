package com.arshop.model;

import java.util.List;


/**
 * This is an generic class which represents the user. This class will have all the informations
 * about the user to be used around the app.
 */
public class User {

    // Account basics for login and identification.
    private String email, password;

    // Informations about the user.
    private String name, address, neighborhood, CEP, contact, address_complement;
    private int age, address_number;

    // Credit Card list.
    private List<CreditCard> user_cards;


    /**
     * User constructor.
     *
     * @param email Email of the user.
     * @param password Password of the user.
     * @param name Name of the user.
     * @param address Address of the user.
     * @param neighborhood Neighborhood of this address.
     * @param CEP CEP of this address.
     * @param contact Contact information of the user.
     * @param address_complement Address complementation if needed.
     * @param age User age.
     * @param address_number User address number.
     * @param user_cards User cards.
     */
    public User(String email, String password, String name, String address, String neighborhood,
                String CEP, String contact, String address_complement, int age, int address_number,
                List<CreditCard> user_cards){

        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
        this.CEP = CEP;
        this.contact = contact;
        this.address_complement = address_complement;
        this.age = age;
        this.address_number = address_number;
        this.user_cards = user_cards;

    }


    /**
     * Add new credit card to the user list.
     *
     * @param credit Credit card type.
     * @param titularName Credit card titular name.
     * @param cardNumber Credit card number.
     * @param expirationDate Credit card expiration date.
     * @param cvv Credit card cvv.
     * @param bornDate Credit Card titular born date.
     * @param CPF Credit card titular CPF.
     */
    public void addCreditCard (CreditCard.Credit credit, String titularName, String cardNumber,
                               String expirationDate, int cvv, String bornDate, String CPF ) {

        CreditCard newCreditCard = new CreditCard(credit, titularName, cardNumber, expirationDate,
                cvv, bornDate, CPF);
        user_cards.add(newCreditCard);

    }


    /**
     * Remove one credit card from the user list.
     *
     * @param index
     */
    public void deleteCreditCard (int index) {
        user_cards.remove(index);
    }


    // User GETTERS functions
    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }


    public String getNeighborhood() {
        return neighborhood;
    }


    public String getCEP() {
        return CEP;
    }


    public String getContact() {
        return contact;
    }


    public String getAddress_complement() {
        return address_complement;
    }


    public int getAddress_number() {
        return address_number;
    }


    public int getAge() {
        return age;
    }


    public List<CreditCard> getUser_cards() {
        return user_cards;
    }



    // User SETTERS functions
    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }


    public void setCEP(String CEP) {
        this.CEP = CEP;
    }


    public void setContact(String contact) {
        this.contact = contact;
    }


    public void setAddress_complement(String address_complement) {
        this.address_complement = address_complement;
    }


    public void setAddress_number(int address_number) {
        this.address_number = address_number;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public void setUser_cards(List<CreditCard> user_cards) {
        this.user_cards = user_cards;
    }
}
