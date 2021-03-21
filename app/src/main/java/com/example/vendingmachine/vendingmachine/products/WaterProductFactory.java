package com.example.vendingmachine.vendingmachine.products;

public class WaterProductFactory implements IProductFactory{
    @Override
    public IProduct create() {
        return new WaterProduct("Живительная водица", 25);
    }
}
