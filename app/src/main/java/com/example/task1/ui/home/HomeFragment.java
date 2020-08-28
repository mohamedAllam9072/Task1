package com.example.task1.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.db.modules.home.Banner;
import com.example.task1.db.modules.home.Category;
import com.example.task1.db.modules.home.Offer;
import com.example.task1.db.modules.home.h_Product;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private View root;
    private RecyclerView rv_products, rv_categories, rv_Offers;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.getHomeData();
        Slider();
        Categories();
        Offers();
        Products();

        return root;
    }

    private void Slider() {
        final SliderAdapter sliderAdapter = new SliderAdapter(getContext());
        viewModel.banners.observe(getViewLifecycleOwner(), new Observer<List<Banner>>() {
            @Override
            public void onChanged(List<Banner> banners) {
                sliderAdapter.setBanners(banners);
            }
        });
        SliderView sliderView = root.findViewById(R.id.imageSlider);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        //set indicator animation by using SliderLayout.IndicatorAnimations.
        // :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    private void Categories() {
        rv_categories = root.findViewById(R.id.rv_categories);
        rv_categories.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        final CategoryAdapter categoryAdapter = new CategoryAdapter(getContext());
        viewModel.categories.observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryAdapter.setList(categories);
                rv_categories.setAdapter(categoryAdapter);
            }
        });
    }

    private void Products() {
        rv_products = root.findViewById(R.id.rv_products);
        rv_products.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        final ProductsAdapter productsAdapter = new ProductsAdapter(getContext());
        viewModel.products.observe(getViewLifecycleOwner(), new Observer<List<h_Product>>() {
            @Override
            public void onChanged(List<h_Product> h_products) {
                productsAdapter.setList(h_products);
                rv_products.setAdapter(productsAdapter);
            }
        });
    }

    private void Offers() {
        rv_Offers = root.findViewById(R.id.rv_offers);
        rv_Offers.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        final OffersAdapter offersAdapter = new OffersAdapter(getContext());
        viewModel.offers.observe(getViewLifecycleOwner(), new Observer<List<Offer>>() {
            @Override
            public void onChanged(List<Offer> offers) {
                offersAdapter.setList(offers);
                rv_Offers.setAdapter(offersAdapter);
            }
        });
    }
}