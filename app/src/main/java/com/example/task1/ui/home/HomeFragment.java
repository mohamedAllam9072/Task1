package com.example.task1.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.databinding.FragmentHomeBinding;
import com.example.task1.db.modules.Favorite;
import com.example.task1.db.modules.home.Banner;
import com.example.task1.db.modules.home.Category;
import com.example.task1.db.modules.home.Offer;
import com.example.task1.db.modules.home.h_Product;
import com.example.task1.ui.Cart.CartActivity;
import com.example.task1.ui.favorite.FavoriteActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private SliderAdapter sliderAdapter;
    private ProductsAdapter productsAdapter;
    private OffersAdapter offersAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_blankFragment);
            }
        });
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getHomeData();
        viewModel.banners.observe(getViewLifecycleOwner(), new Observer<List<Banner>>() {
            @Override
            public void onChanged(List<Banner> banners) {
                sliderAdapter.setBanners(banners);
            }
        });
        Slider();
        Categories();
        Offers();
        Products();
        AddToFavorite();
        AddToCart();

        return binding.getRoot();
    }

    private void AddToFavorite() {
        productsAdapter.setOnFavoriteButtonClicked(new ProductsAdapter.onFavoriteClicked() {
            @Override
            public void m_onClick(h_Product h_product) {
                Intent intent = new Intent(getContext(), FavoriteActivity.class);
                viewModel.insert(new Favorite(h_product.getId(), h_product.getName(), h_product.getImage(), h_product.getPrice()));
                Toast.makeText(getContext(), "add to Favorites", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        offersAdapter.setOnFavoriteButtonClicked(new OffersAdapter.onFavoriteClicked() {
            @Override
            public void m_onClick(Offer offer) {
                Intent intent = new Intent(getContext(), FavoriteActivity.class);
                viewModel.insert(new Favorite(offer.getId(), offer.getName(), offer.getImage(), offer.getPrice()));
                Toast.makeText(getContext(), "add to Favorites", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }

    private void AddToCart() {
        productsAdapter.setOnCartButtonClicked(new ProductsAdapter.onCartClicked() {
            @Override
            public void m_onClick(h_Product h_product) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                viewModel.insert(new Favorite(h_product.getId(), h_product.getName(), h_product.getImage(), h_product.getPrice()));
                Toast.makeText(getContext(), "add to cart", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        offersAdapter.setOnCartButtonClicked(new OffersAdapter.onCartClicked() {
            @Override
            public void m_onClick(Offer offer) {
                Intent intent = new Intent(getContext(), FavoriteActivity.class);
                viewModel.insert(new Favorite(offer.getId(), offer.getName(), offer.getImage(), offer.getPrice()));
                Toast.makeText(getContext(), "add to cart", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }

    private void Slider() {
        viewModel.banners.observe(getViewLifecycleOwner(), new Observer<List<Banner>>() {
            @Override
            public void onChanged(List<Banner> banners) {
                sliderAdapter.setBanners(banners);
            }
        });
        sliderAdapter = new SliderAdapter(getContext());
        binding.imageSlider.setSliderAdapter(sliderAdapter);
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        //set indicator animation by using SliderLayout.IndicatorAnimations.
        // :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        binding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        binding.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        binding.imageSlider.startAutoCycle();
    }

    private void Categories() {
        binding.rvCategories.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        final CategoryAdapter categoryAdapter = new CategoryAdapter(getContext());
        viewModel.categories.observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryAdapter.setList(categories);
                binding.rvCategories.setAdapter(categoryAdapter);
            }
        });
    }

    private void Products() {
        binding.rvProducts.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        productsAdapter = new ProductsAdapter(getContext());
        viewModel.products.observe(getViewLifecycleOwner(), new Observer<List<h_Product>>() {
            @Override
            public void onChanged(List<h_Product> h_products) {
                productsAdapter.setList(h_products);
                binding.rvProducts.setAdapter(productsAdapter);
            }
        });
    }

    private void Offers() {
        binding.rvOffers.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        offersAdapter = new OffersAdapter(getContext());
        viewModel.offers.observe(getViewLifecycleOwner(), new Observer<List<Offer>>() {
            @Override
            public void onChanged(List<Offer> offers) {
                offersAdapter.setList(offers);
                binding.rvOffers.setAdapter(offersAdapter);
            }
        });
    }
}