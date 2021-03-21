package com.example.vendingmachine;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vendingmachine.databinding.FragmentBlankBinding;
import com.example.vendingmachine.vendingmachine.IVendingMachine;

public class BlankFragment extends Fragment {

    private FragmentBlankBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false);

        if (getArguments()!=null){
            IVendingMachine vendingMachine = (IVendingMachine) getArguments().get("vending");
            vendingMachine.processClient();
            binding.setVendingMachine(vendingMachine);
            //mySleep(200);
        }else{
            Toast.makeText(getContext(), "Фрагмент не получил объект vendingMachine", Toast.LENGTH_SHORT).show();
        }

        return binding.getRoot();
    }

    private void mySleep(long timeInterval){
        try {
            Thread.sleep(timeInterval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}