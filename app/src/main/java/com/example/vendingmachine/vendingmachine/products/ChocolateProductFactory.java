package com.example.vendingmachine.vendingmachine.products;

public class ChocolateProductFactory implements IProductFactory{
    @Override
    public IProduct create() {
        return new ChocolateProduct("Толстый сладкий чёрный батончик", 75);
    }
}
