package com.example.vendingmachine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendingmachine.databinding.ItemStudentBinding;

import java.util.List;
import java.util.zip.Inflater;

public class QueueAdapter extends RecyclerView.Adapter<QueueAdapter.StudentViewHolder> {

    private List<Student> students;

    public void setContent(List<Student> students){
        this.students=students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStudentBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_student, parent, false);
        return new StudentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bindStudent(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder{

        ItemStudentBinding binding;

        public StudentViewHolder(@NonNull ItemStudentBinding binding) {
            super(binding.cardView);
            this.binding=binding;
        }

        void bindStudent(Student student){
            binding.setStudent(student);
        }
    }
}
