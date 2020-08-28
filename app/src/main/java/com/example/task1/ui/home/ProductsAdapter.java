package com.example.task1.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.db.modules.productDetails.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.mVH> {
    private ArrayList<Product> products;
    private Context context;

    public ProductsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public mVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new mVH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull mVH holder, int position) {
        try {
            Picasso.with(context)
                    .load(products.get(position).getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);
        } catch (Exception ignored) {
        }
        holder.tv_name.setText(products.get(position).getName());
        holder.tv_price.setText(products.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setList(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public class mVH extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_name, tv_price;
        ImageButton ib_favorite, ib_cart;

        public mVH(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_main_product);
            tv_name = itemView.findViewById(R.id.tv_title_product);
            tv_price = itemView.findViewById(R.id.tv_price_product);
            ib_favorite = itemView.findViewById(R.id.ib_favorite_product);
            ib_cart = itemView.findViewById(R.id.ib_cart_product);

        }
    }
}
