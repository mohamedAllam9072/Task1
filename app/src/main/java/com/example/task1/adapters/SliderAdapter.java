package com.example.task1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.task1.R;
import com.example.task1.db.modules.home.Banner;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.mVH> {
    private Context context;
    private List<Banner> banners = new ArrayList<>();

    public SliderAdapter(Context context) {
        this.context = context;
    }


    @Override
    public mVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false);
        return new mVH(view);
    }

    @Override
    public void onBindViewHolder(mVH holder, int position) {
        try {
            Picasso.with(context)
                    .load(banners.get(position).getImage())
                    .placeholder(R.drawable.logo_ac)
                    .error(R.drawable.logo_ac)
                    .fit()
                    .into(holder.imageView);
        } catch (Exception ignored) {
        }
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
        notifyDataSetChanged();
    }

    public class mVH extends SliderViewAdapter.ViewHolder {
        ImageView imageView;

        public mVH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_slider_item);
        }
    }
}
