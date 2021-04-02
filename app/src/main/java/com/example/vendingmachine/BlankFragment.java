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
import com.example.vendingmachine.vendingmachine.products.IProduct;

import java.util.List;

public class BlankFragment extends Fragment {

    private FragmentBlankBinding binding;
    private QueueAdapter queueAdapter;
    private ProductsAdapter productsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false);

        queueAdapter = new QueueAdapter();
        productsAdapter=new ProductsAdapter();

        binding.queueRecyclerView.setAdapter(queueAdapter);
        binding.queueRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.productsRecyclerView.setAdapter(productsAdapter);
        binding.productsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getArguments()!=null){
            IVendingMachine vendingMachine = (IVendingMachine) getArguments().get("vending");

            // привызываемся на изменение контента для адаптеров
            vendingMachine.getQueue().observe(getViewLifecycleOwner(), this::updateQueueAdapterContent);
            vendingMachine.getChosenProducts().observe(getViewLifecycleOwner(), this::updateProductsAdapterContent);
            // подписка на запрос для открытия фрагмента
            vendingMachine.getRequestToOpenFragment().observe(getViewLifecycleOwner(), this::onFragmentClick);

            // запускается обработка первого в очереди
             if (!getArguments().getBoolean("isStarted")) vendingMachine.processClient();
             // во view добавлятеся объект, содержащий все даные для него
            binding.setVendingMachine(vendingMachine);
            // указывается имя автомата
            binding.vendingName.setText((String) getArguments().get("vendingName"));
            //mySleep(200);
        }else{
            Toast.makeText(getContext(), "Фрагмент не получил объект vendingMachine", Toast.LENGTH_SHORT).show();
        }

        return binding.getRoot();
    }

    // заменяет объект на текущий
    public void onFragmentClick(Boolean indicator){
        if (!indicator){
            return;
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        Fragment fragment = new BlankFragment();
        int currentIndex = getArguments().getInt("index");

        Bundle data = new Bundle();
        data.putSerializable("vending", Campus.getInstance().getVendingMachine(currentIndex-1));
        data.putString("vendingName", "Торговый автомат "+currentIndex);
        data.putInt("index", currentIndex);
        data.putBoolean("isStarted", true);

        fragment.setArguments(data);

        getFragmentManager().beginTransaction()
                .replace(R.id.bigFragmentContainer, fragment, "bigFragment")
                .addToBackStack("bigFragmentTransaction")
                .commit();
    }

    private void updateQueueAdapterContent(List<Student> students){
        queueAdapter.setContent(students);
    }
    private void updateProductsAdapterContent(List<IProduct> products){
        productsAdapter.setContent(products);
    }
}