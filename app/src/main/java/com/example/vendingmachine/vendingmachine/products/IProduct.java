package com.example.vendingmachine.vendingmachine.products;

public class IProduct {
    private String name;
    private int price;

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
}
