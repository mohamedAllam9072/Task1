package com.example.task1.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.db.modules.home.h_Product;
import com.example.task1.ui.ProductDetails.ProductDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.mVH> implements Filterable {
    private List<h_Product> list1 = new ArrayList<>();
    private List<h_Product> list2;
    private Context context;
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<h_Product> filtered_list = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filtered_list.addAll(list2);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (h_Product data_item : list2) {
                    if (data_item.getName().toLowerCase().contains(filterPattern)) {
                        filtered_list.add(data_item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered_list;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list1.clear();
            list1.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public SearchAdapter(Context context) {
        this.context = context;
        list2 = new ArrayList<>(list1);
    }

    @NonNull
    @Override
    public mVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search, parent, false);
        mVH mVH = new mVH(view);
        return mVH;
    }

    @Override
    public void onBindViewHolder(@NonNull mVH holder, int position) {
        holder.textView1.setText(list1.get(position).getName());
        holder.textView2.setText("price: " + list1.get(position).getPrice());
        try {
            Picasso.with(context)
                    .load(list1.get(position).getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);
        } catch (Exception ignored) {
        }
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public void setList(List<h_Product> list1) {
        this.list1 = list1;
        notifyDataSetChanged();
    }

    public class mVH extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3;
        ImageView imageView;

        public mVH(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.tv1_search);
            textView2 = itemView.findViewById(R.id.tv2_search);
            textView3 = itemView.findViewById(R.id.tv3_search);
            imageView = itemView.findViewById(R.id.iv_search);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("id", list1.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
