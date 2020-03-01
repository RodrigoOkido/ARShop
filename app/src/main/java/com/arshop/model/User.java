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
    private String name, cpf, bornDate, contact;
    private int age;

    // Addresses List
    private List<Address> addresses;

    // Credit Card list.
    private List<CreditCard> user_cards;


    /**
     * User constructor.
     *
     * @param email Email of the user.
     * @param password Password of the user.
     * @param name Name of the user.
     * @param contact Contact information of the user.
     * @param age User age.
     * @param user_cards User cards.
     */
    public User(String email, String password, String name, String cpf, String bornDate,
                String contact, int age, List<Address> addresses, List<CreditCard> user_cards){

        this.email = email;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.bornDate = bornDate;
        this.contact = contact;
        this.age = age;
        this.addresses = addresses;
        this.user_cards = user_cards;

    }


    /**
     * Add new Address to the addresses list.
     *
     * @param address New address name.
     * @param neighborhood Address neighborhood.
     * @param CEP Address CEP.
     * @param address_complement Address complement.
     * @param address_number Address number.
     */
    public void addAddress (String address, String neighborhood, String CEP, String address_complement,
                            int address_number) {

        Address newAddress = new Address (address, neighborhood, CEP, address_complement, address_number);
        addresses.add(newAddress);
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
     * Remove one address from the addresses list.
     *
     * @param index Index of the addresses list.
     */
    public void deleteAddress (int index) {
        addresses.remove(index);
    }


    /**
     * Remove one credit card from the credit card list.
     *
     * @param index Index of the credit card list.
     */
    public void deleteCreditCard (int index) {
        user_cards.remove(index);
    }


    // User GETTERS functions.
    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getName() {
        return name;
    }


    public String getCpf() {
        return cpf;
    }


    public String getBornDate() {
        return bornDate;
    }


    public String getContact() {
        return contact;
    }


    public int getAge() {
        return age;
    }


    public List<Address> getAddresses() {
        return addresses;
    }


    public List<CreditCard> getUser_cards() {
        return user_cards;
    }


    // User SETTERS functions.
    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }


    public void setContact(String contact) {
        this.contact = contact;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    public void setUser_cards(List<CreditCard> user_cards) {
        this.user_cards = user_cards;
    }


}
