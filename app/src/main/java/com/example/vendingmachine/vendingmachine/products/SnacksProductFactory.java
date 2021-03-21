package com.example.vendingmachine.vendingmachine.products;

public class SnacksProductFactory implements IProductFactory {
    @Override
    public IProduct create(){
        return new SnacksProduct("Закуска под право", 33);
    }
}
