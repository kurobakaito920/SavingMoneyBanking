package com.example.savingmoneybanking.model;

public class SavingBanking {
    private int idSavingBank;
    private String walletSavingBank;
    private String monthSendingSavingBank;
    private String percentOnMonthBank;
    private String moneySendToBank;
    private String methodPaymentBank;

    public SavingBanking(int idSavingBank, String walletSavingBank, String monthSendingSavingBank, String percentOnMonthBank, String moneySendToBank, String methodPaymentBank) {
        this.idSavingBank = idSavingBank;
        this.walletSavingBank = walletSavingBank;
        this.monthSendingSavingBank = monthSendingSavingBank;
        this.percentOnMonthBank = percentOnMonthBank;
        this.moneySendToBank = moneySendToBank;
        this.methodPaymentBank = methodPaymentBank;
    }

    public SavingBanking() {
    }

    public int getIdSavingBank() {
        return idSavingBank;
    }

    public void setIdSavingBank(int idSavingBank) {
        this.idSavingBank = idSavingBank;
    }

    public String getWalletSavingBank() {
        return walletSavingBank;
    }

    public void setWalletSavingBank(String walletSavingBank) {
        this.walletSavingBank = walletSavingBank;
    }

    public String getMonthSendingSavingBank() {
        return monthSendingSavingBank;
    }

    public void setMonthSendingSavingBank(String monthSendingSavingBank) {
        this.monthSendingSavingBank = monthSendingSavingBank;
    }

    public String getPercentOnMonthBank() {
        return percentOnMonthBank;
    }

    public void setPercentOnMonthBank(String percentOnMonthBank) {
        this.percentOnMonthBank = percentOnMonthBank;
    }

    public String getMoneySendToBank() {
        return moneySendToBank;
    }

    public void setMoneySendToBank(String moneySendToBank) {
        this.moneySendToBank = moneySendToBank;
    }

    public String getMethodPaymentBank() {
        return methodPaymentBank;
    }

    public void setMethodPaymentBank(String methodPaymentBank) {
        this.methodPaymentBank = methodPaymentBank;
    }
}
