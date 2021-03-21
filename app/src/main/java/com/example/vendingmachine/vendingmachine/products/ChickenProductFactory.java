package com.example.vendingmachine.vendingmachine.products;

public class ChickenProductFactory implements IProductFactory{
    @Override
    public IProduct create() {
        return new ChickenProduct("Райская курочка", 666);
    }
}
