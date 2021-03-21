package com.example.vendingmachine;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vendingmachine.databinding.FragmentBlankBinding;
import com.example.vendingmachine.vendingmachine.IVendingMachine;

import java.util.List;

public class BlankFragment extends Fragment {

    private FragmentBlankBinding binding;
    private QueueAdapter queueAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false);
        queueAdapter = new QueueAdapter();

        binding.queueRecyclerView.setAdapter(queueAdapter);
        binding.queueRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getArguments()!=null){
            IVendingMachine vendingMachine = (IVendingMachine) getArguments().get("vending");

            vendingMachine.getQueue().observe(getViewLifecycleOwner(), this::updateQueueAdapterContent);

            vendingMachine.processClient();
            binding.setVendingMachine(vendingMachine);
            //mySleep(200);
        }else{
            Toast.makeText(getContext(), "Фрагмент не получил объект vendingMachine", Toast.LENGTH_SHORT).show();
        }

        return binding.getRoot();
    }

    private void updateQueueAdapterContent(List<Student> students){
        queueAdapter.setContent(students);
    }
    private void mySleep(long timeInterval){
        try {
            Thread.sleep(timeInterval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}