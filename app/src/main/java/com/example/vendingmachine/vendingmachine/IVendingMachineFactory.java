package com.example.vendingmachine.vendingmachine;

import com.example.vendingmachine.Student;
import com.example.vendingmachine.vendingmachine.products.IProductFactory;

import java.util.List;

public interface IVendingMachineFactory {
    IVendingMachine create(List<Student> studentNames, List<IProductFactory> productFactories, int numberOfOneProductCategory);
}
