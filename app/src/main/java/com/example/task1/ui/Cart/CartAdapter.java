package com.example.task1.ui.Cart;

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
import com.example.task1.db.modules.Cart.Cart_product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.mVH> {
    private List<Cart_product> cart_products = new ArrayList<>();
    private Context context;
    private onCartClicked listener;


    public CartAdapter(Context context) {
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
                    .load(cart_products.get(position).getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);
        } catch (Exception ignored) {
        }
        holder.tv_name.setText(cart_products.get(position).getName());
        holder.tv_price.setText(cart_products.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return cart_products.size();
    }

    public void setList(List<Cart_product> cart_products) {
        this.cart_products = cart_products;
        notifyDataSetChanged();
    }

    public Cart_product getCartProductAt(int position) {
        return cart_products.get(position);
    }

    public void setOnCartButtonClicked(onCartClicked listener) {
        this.listener = listener;
    }

    public interface onCartClicked {
        void m_onClick(Cart_product cart_product);
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.m_onClick(cart_products.get(position));
                    }
                }
            });

        }
    }


}
