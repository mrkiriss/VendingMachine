package com.example.vendingmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.vendingmachine.vendingmachine.IVendingMachine;

public class MainActivity extends AppCompatActivity {

    private Campus campus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campus=Campus.getInstance();
        initFragments();
    }

    private void initFragments(){
        Fragment fragment1 = new BlankFragment();
        Bundle data1 = new Bundle();
        data1.putSerializable("vending", campus.getVendingMachine(0));
        data1.putString("vendingName", "Торговый автомат 1");
        data1.putInt("index", 1);
        data1.putBoolean("isStarted", false);

        fragment1.setArguments(data1);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1, fragment1, "fragment1").commit();

        Fragment fragment2 = new BlankFragment();
        Bundle data2 = new Bundle();
        data2.putSerializable("vending", campus.getVendingMachine(1));
        data2.putString("vendingName", "Торговый автомат 2");
        data2.putInt("index", 2);
        data2.putBoolean("isStarted", false);

        fragment2.setArguments(data2);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container2, fragment2, "fragment2").commit();

        Fragment fragment3 = new BlankFragment();
        Bundle data3 = new Bundle();
        data3.putSerializable("vending", campus.getVendingMachine(2));
        data3.putString("vendingName", "Торговый автомат 3");
        data3.putInt("index", 3);
        data3.putBoolean("isStarted", false);

        fragment3.setArguments(data3);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container3, fragment3, "fragment3").commit();

        Fragment fragment4 = new BlankFragment();
        Bundle data4 = new Bundle();
        data4.putSerializable("vending", campus.getVendingMachine(3));
        data4.putString("vendingName", "Торговый автомат 4");
        data4.putInt("index", 4);
        data4.putBoolean("isStarted", false);
        fragment4.setArguments(data4);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container4, fragment4, "fragment4").commit();
    }
}