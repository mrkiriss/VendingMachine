package com.example.vendingmachine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendingmachine.databinding.ItemProductBinding;
import com.example.vendingmachine.vendingmachine.products.IProduct;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    List<IProduct> products;

    public void setContent(List<IProduct> products){
        this.products=products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_product, parent, false);

        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bindProduct(products.get(position));
    }

    @Override
    public int getItemCount() {
        if (products==null) return 0;
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        private ItemProductBinding binding;

        public ProductViewHolder(@NonNull ItemProductBinding binding) {
            super(binding.cardViewProduct);
            this.binding=binding;
        }

        public void bindProduct(IProduct product){
            binding.setProduct(product);
        }
    }
}
