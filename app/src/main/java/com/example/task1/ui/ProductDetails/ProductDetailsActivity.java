package com.example.task1.ui.ProductDetails;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.db.modules.Favorite;
import com.example.task1.db.modules.home.Banner;
import com.example.task1.db.modules.productDetails.Related;
import com.example.task1.db.modules.productDetails.colors;
import com.example.task1.ui.Cart.CartActivity;
import com.example.task1.ui.favorite.FavoriteActivity;
import com.example.task1.ui.home.SliderAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private FloatingActionButton fab_favorite;
    private MaterialCardView cardView;
    private int id;
    private RelatedAdapter relatedAdapter;

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
        fab_favorite();
        add_to_cart();
        addToFavorite();

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
        relatedAdapter = new RelatedAdapter(this);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    }

    private void fab_favorite() {
        fab_favorite = findViewById(R.id.fab_favorite);
        fab_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                viewModel.insert(new Favorite(id, viewModel.name.getValue(), viewModel.image.getValue(), viewModel.price.getValue()));
                Toast.makeText(getApplicationContext(), "add to Favorites", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    private void addToFavorite() {
        relatedAdapter.setOnFavoriteButtonClicked(new RelatedAdapter.onFavoriteClicked() {
            @Override
            public void m_onClick(Related related) {
                Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                viewModel.insert(new Favorite(related.getId(), related.getName(), related.getImage(), related.getPrice()));
                Toast.makeText(getApplicationContext(), "add to Favorites", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

        });
    }

    private void add_to_cart() {
        cardView = findViewById(R.id.card_add_to_cart);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_product_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_act_prod_details_share:
                Toast.makeText(this, "share ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_main_act_prod_details_cart:
                Intent intent2 = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}