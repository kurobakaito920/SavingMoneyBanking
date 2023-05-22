package com.example.savingmoneybanking.model;

public class SpendingTerm {
    private int idSendingterm;
    private String monthsending;
    private String percentage;

    public SpendingTerm(int idSendingterm, String monthsending, String percentage) {
        this.idSendingterm = idSendingterm;
        this.monthsending = monthsending;
        this.percentage = percentage;
    }

    public SpendingTerm() {
    }

    public int getIdSendingterm() {
        return idSendingterm;
    }

    public void setIdSendingterm(int idSendingterm) {
        this.idSendingterm = idSendingterm;
    }

    public String getMonthsending() {
        return monthsending;
    }

    public void setMonthsending(String monthsending) {
        this.monthsending = monthsending;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
