package com.example.vendingmachine;

import com.example.vendingmachine.vendingmachine.IVendingMachine;
import com.example.vendingmachine.vendingmachine.IVendingMachineFactory;
import com.example.vendingmachine.vendingmachine.VendingMachineFactory;
import com.example.vendingmachine.vendingmachine.products.ChickenProductFactory;
import com.example.vendingmachine.vendingmachine.products.ChocolateProductFactory;
import com.example.vendingmachine.vendingmachine.products.IProductFactory;
import com.example.vendingmachine.vendingmachine.products.SnacksProductFactory;
import com.example.vendingmachine.vendingmachine.products.WaterProductFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Campus {
    private static Campus instance;

    private final int NUMBER_OF_VENDING_MACHINES=4;
    private final List<IProductFactory> productFactories;
    private final int NUMBER_OF_PRODUCT_ONE_CATEGORY=0;
    private final List<Student> students;

    private List<IVendingMachine> vendingMachines;

    private Campus(){
        students = createStudents();
        productFactories= Arrays.asList(new ChocolateProductFactory(), new WaterProductFactory(), new ChickenProductFactory(), new SnacksProductFactory());

        vendingMachines = initVendingMachine();
    }

    private List<IVendingMachine> initVendingMachine(){
        ArrayList<IVendingMachine> vendingMachines = new ArrayList<>();
        IVendingMachineFactory factory = new VendingMachineFactory();

        List<List<Student>> splitedStudents = splitStudents();
        for (int i=0;i<NUMBER_OF_VENDING_MACHINES;i++){
            vendingMachines.add(factory.create(splitedStudents.get(i),productFactories , NUMBER_OF_PRODUCT_ONE_CATEGORY));
        }

        return vendingMachines;
    }
    private List<List<Student>> splitStudents(){
        List<List<Student>> result = new ArrayList<>();
        for (int i = 0; i< students.size(); i+= students.size()/NUMBER_OF_VENDING_MACHINES){
            result.add(new ArrayList<>(students.subList(i, Math.min(students.size(), i+students.size()/NUMBER_OF_VENDING_MACHINES))));
        }
        return result;
    }
    public List<Student> createStudents(){
        LinkedList<Student> result = new LinkedList<>();
        for (String student: Constants.studentsInStrings){
            String[] name_id=student.split("_");
            result.add(new Student(name_id[0], name_id[1]));
        }

        return  result;
    }

    public static Campus getInstance(){
        if (instance==null){
            instance=new Campus();
        }
        return instance;
    }

    public IVendingMachine getVendingMachine(int index){
        return vendingMachines.get(index);
    }
}
