package com.example.task1.ui.ProductDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.db.modules.productDetails.colors;

import java.util.ArrayList;
import java.util.List;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.mVH> {
    private List<colors> colors = new ArrayList<>();
    private Context context;

    public ColorsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public mVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_color, parent, false);
        return new mVH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull mVH holder, int position) {
        holder.tv_name.setText(colors.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public void setList(List<colors> colors) {
        this.colors = colors;
        notifyDataSetChanged();
    }

    public class mVH extends RecyclerView.ViewHolder {
        TextView tv_name;

        public mVH(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_color_name);

        }
    }
}
