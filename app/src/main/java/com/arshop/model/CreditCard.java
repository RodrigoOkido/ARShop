package com.arshop.model;

public class CreditCard {

    // Enum the types of Credit Cards.
    enum Credit {
        MASTERCARD,
        VISA
    }

    // Informations about the Credit Card.
    private Credit credit;
    private String titularName, cardNumber, expirationDate,  bornDate, CPF;
    private int cvv;


    /**
     * CreditCard constructor.
     *
     * @param credit Credit card type.
     * @param titularName Credit card titular name.
     * @param cardNumber Credit card number.
     * @param expirationDate Credit card expiration date.
     * @param cvv Credit card cvv.
     * @param bornDate Credit Card titular born date.
     * @param CPF Credit card titular CPF.
     */
    public CreditCard(Credit credit, String titularName, String cardNumber, String expirationDate,
                      int cvv, String bornDate, String CPF ) {

        this.credit = credit;
        this.titularName = titularName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.bornDate = bornDate;
        this.CPF = CPF;

    }


    // CreditCard GETTERS functions
    public Credit getCredit() {
        return credit;
    }


    public String getTitularName() {
        return titularName;
    }


    public String getCardNumber() {
        return cardNumber;
    }


    public String getExpirationDate() {
        return expirationDate;
    }


    public int getCvv() {
        return cvv;
    }


    public String getBornDate() {
        return bornDate;
    }


    public String getCPF() {
        return CPF;
    }



    // CreditCard SETTERS functions
    public void setCredit(Credit credit) {
        this.credit = credit;
    }


    public void setTitularName(String titularName) {
        this.titularName = titularName;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }


    public void setCvv(int cvv) {
        this.cvv = cvv;
    }


    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }


    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

}
