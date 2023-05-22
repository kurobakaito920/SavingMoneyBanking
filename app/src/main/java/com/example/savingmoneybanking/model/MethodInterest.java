package com.example.savingmoneybanking.model;

public class MethodInterest {
    private int id_method_interest;
    private String name_method_payment;

    public MethodInterest(int id_method_interest, String name_method_payment) {
        this.id_method_interest = id_method_interest;
        this.name_method_payment = name_method_payment;
    }

    public MethodInterest() {
    }

    public int getId_method_interest() {
        return id_method_interest;
    }

    public void setId_method_interest(int id_method_interest) {
        this.id_method_interest = id_method_interest;
    }

    public String getName_method_payment() {
        return name_method_payment;
    }

    public void setName_method_payment(String name_method_payment) {
        this.name_method_payment = name_method_payment;
    }
}
