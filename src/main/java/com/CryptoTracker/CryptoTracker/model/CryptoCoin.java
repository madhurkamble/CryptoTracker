package com.CryptoTracker.CryptoTracker.model;

public class CryptoCoin {
    private String id;
    private String name;
    private double price;
    private double changein24Hrs;

    public CryptoCoin() {
    }

    public CryptoCoin(String id, String name, double price, double changein24Hrs) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.changein24Hrs = changein24Hrs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getChangein24Hrs() {
        return changein24Hrs;
    }

    public void setChangein24Hrs(double changein24Hrs) {
        this.changein24Hrs = changein24Hrs;
    }
    
}
