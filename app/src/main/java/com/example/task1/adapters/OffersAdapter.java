package com.example.task1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.db.modules.home.Offer;
import com.example.task1.ui.ProductDetails.ProductDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.mVH> {
    private List<Offer> offers = new ArrayList<>();
    private Context context;
    private onFavoriteClicked listener;
    private onCartClicked listener2;

    public OffersAdapter(Context context) {
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
                    .load(offers.get(position).getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);
        } catch (Exception ignored) {
        }
        holder.tv_name.setText(offers.get(position).getName());
        holder.tv_price.setText(offers.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public void setList(List<Offer> offers) {
        this.offers = offers;
        notifyDataSetChanged();
    }

    public void setOnFavoriteButtonClicked(onFavoriteClicked listener) {
        this.listener = listener;
    }

    public interface onFavoriteClicked {
        void m_onClick(Offer offer);
    }

    public void setOnCartButtonClicked(onCartClicked listener2) {
        this.listener2 = listener2;
    }

    public interface onCartClicked {
        void m_onClick(Offer offer);
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
            ib_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.m_onClick(offers.get(position));
                    }
                }
            });
            ib_cart = itemView.findViewById(R.id.ib_cart_product);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("id", offers.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
