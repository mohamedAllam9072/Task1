package com.example.task1.ui.ProductDetails;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.db.modules.home.Banner;
import com.example.task1.db.modules.productDetails.Related;
import com.example.task1.db.modules.productDetails.colors;
import com.example.task1.ui.home.SliderAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ProductDetailsActivity";
    private ProductDetailsViewModel viewModel;
    private TextView tv_bio, tv_name, tv_price, tv_rate;
    private String product_name;
    private RecyclerView rv_related, rv_colors;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        actionbar();
        viewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel.class);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 1);
        viewModel.getProductDetails(id);
        Related();
        colors();
        texts();
        Slider();
    }

    private void texts() {
        tv_name = findViewById(R.id.tv_title_p_details);
        tv_price = findViewById(R.id.tv_price_p_details);
        tv_rate = findViewById(R.id.tv_rate);
        tv_bio = findViewById(R.id.tv_bio);
        viewModel.name.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                product_name = s;
                tv_name.setText(product_name);
            }
        });
        viewModel.price.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv_price.setText(s + " جنية");
            }
        });
        viewModel.rate.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv_rate.setText("" + integer);
            }
        });
        viewModel.bio.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv_bio.setText(s);
            }
        });


    }

    private void Related() {
        rv_related = findViewById(R.id.rv_related);
        rv_related.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        final RelatedAdapter relatedAdapter = new RelatedAdapter(this);
        viewModel.related.observe(this, new Observer<List<Related>>() {
            @Override
            public void onChanged(List<Related> relateds) {
                relatedAdapter.setList(relateds);
                rv_related.setAdapter(relatedAdapter);
            }
        });
    }

    private void colors() {
        rv_colors = findViewById(R.id.rv_colors);
        rv_colors.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        final ColorsAdapter colorsAdapter = new ColorsAdapter(this);
        viewModel.colors.observe(this, new Observer<List<colors>>() {
            @Override
            public void onChanged(List<colors> colors) {
                colorsAdapter.setList(colors);
                rv_colors.setAdapter(colorsAdapter);
            }
        });
    }

    private void Slider() {
        final SliderAdapter sliderAdapter = new SliderAdapter(this);
        viewModel.images.observe(this, new Observer<List<Banner>>() {
            @Override
            public void onChanged(List<Banner> banners) {
                sliderAdapter.setBanners(banners);
            }
        });
        SliderView sliderView = findViewById(R.id.imageSlider2);
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

    private void actionbar() {
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(product_name);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
        appBarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigation.findNavController(root).navigate(R.id.action_navigation_home_to_countryFragment);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_product_details, menu);
        return true;
    }
}