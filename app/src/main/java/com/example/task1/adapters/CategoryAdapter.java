package com.example.task1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.databinding.ItemCategoryBinding;
import com.example.task1.db.modules.home.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.mVH> {
    private List<Category> categories = new ArrayList<>();
    private Context context;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public mVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),R.layout.item_category,parent,false);
        return new mVH(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull mVH holder, int position) {
        holder.binding.tvCategory.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setList(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public class mVH extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;

        public mVH(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding =binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, categories.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
