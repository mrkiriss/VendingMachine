package com.example.vendingmachine.vendingmachine;

import com.example.vendingmachine.Student;
import com.example.vendingmachine.vendingmachine.products.IProduct;
import com.example.vendingmachine.vendingmachine.products.IProductFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class VendingMachineFactory implements IVendingMachineFactory{

    @Override
    public IVendingMachine create(List<Student> studentNames, List<IProductFactory> productFactories, int numberOfOneProductCategory) {
        HashMap<IProduct, Integer> products=new HashMap<>();
        for (IProductFactory productFactory:productFactories){
            products.put(productFactory.create(), numberOfOneProductCategory);
        }
        return new IVendingMachine(new LinkedList<>(studentNames), products);
    }
}
