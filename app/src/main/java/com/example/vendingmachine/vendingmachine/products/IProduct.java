package com.example.vendingmachine.vendingmachine.products;

public class IProduct {
    private String name;
    private int price;
    private int number;

    public IProduct(String name, int price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
