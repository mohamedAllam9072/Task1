package com.example.task1.ui.home;

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

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.mVH> {
    private Context context;
    private ArrayList<Banner> hBanners;

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
                    .load(hBanners.get(position).getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);
        } catch (Exception ignored) {
        }
    }

    @Override
    public int getCount() {
        return hBanners.size();
    }

    public void sethBanners(ArrayList<Banner> hBanners) {
        this.hBanners = hBanners;
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
