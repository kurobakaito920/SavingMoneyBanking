package com.example.savingmoneybanking.model;

public class Wallet {
    private int id_wallet;
    private String namewallet;
    private String prices;



    public Wallet(int id_wallet, String namewallet, String prices) {
        this.id_wallet = id_wallet;
        this.namewallet = namewallet;
        this.prices = prices;
    }

    public Wallet(String namewallet, String prices) {
        this.namewallet = namewallet;
        this.prices = prices;
    }
    public Wallet(String s){}

    public int getId_wallet() {
        return id_wallet;
    }

    public void setId_wallet(int id_wallet) {
        this.id_wallet = id_wallet;
    }

    public String getNamewallet() {
        return namewallet;
    }

    public void setNamewallet(String namewallet) {
        this.namewallet = namewallet;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id_wallet=" + id_wallet +
                ", namewallet='" + namewallet + '\'' +
                ", prices='" + prices + '\'' +
                '}';
    }
}
