package com.example.miku_project.screens.fragment_screens.home;

import static com.example.miku_project.helper.Config.BASE_URL;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;
import com.example.miku_project.adapters.AdapterRecentlyTopMusic;
import com.example.miku_project.adapters.Adapter_Album;
import com.example.miku_project.adapters.Adapter_Category;
import com.example.miku_project.adapters.Adapter_Product;
import com.example.miku_project.adapters.Adapter_Product2;
import com.example.miku_project.adapters.Adapter_Recommend;
import com.example.miku_project.adapters.Adapter_Songs;
import com.example.miku_project.adapters.Adapter_Theme;
import com.example.miku_project.models.Album;
import com.example.miku_project.models.Category;
import com.example.miku_project.models.Product;
import com.example.miku_project.models.ProductCategory;
import com.example.miku_project.models.Recommend;
import com.example.miku_project.models.Theme;
import com.example.miku_project.models.ThemeCategory;
import com.example.miku_project.models.TopMusicRecentlyModel;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;
import com.example.miku_project.screens.fragment_screens.home.banner.BannerModel;
import com.example.miku_project.screens.fragment_screens.home.banner.SliderBannerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager2;
    private List<BannerModel> sliderItems;
    private Handler handler = new Handler();

    // Top music recently
    private List<TopMusicRecentlyModel> topMusicRecentlyModels;
    private AdapterRecentlyTopMusic adapterRecentlyTopMusic;
    private RecyclerView rcvTopMusicRecently;

    private Adapter_Product adapter_product;
    private Adapter_Product2 adapter_product2;
    private Adapter_Album adapter_album;
    private Adapter_Category adapter_category;
    private Adapter_Theme adapter_theme;
    private Adapter_Songs adapter_songs;
    private Adapter_Recommend adapter_recommend;
    private RecyclerView rcv_recommend, rcv_category, rcv_album;

    private ArrayList<Product> product_data;
    private ArrayList<ProductCategory> category_data;
    private ArrayList<Recommend> recommend_data;
    private ArrayList<Album> album_data;
    private ArrayList<Theme> theme_data;

    HorizontalScrollView horizontalScrollView;

    private IRetrofitService service;
    private SharedPreferences sharePref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Init view
        initView(view);        //service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharePref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(getActivity());
        requestSliderBanner();
        loadListTopMusicRecently();
//        rcvRecommend();
//        rcvAlbum();
//        rcvTheme();
    }

    private void requestSliderBanner() {
        sliderItems = new ArrayList<>();
        sliderItems.add(new BannerModel(R.drawable.pop));
        sliderItems.add(new BannerModel(R.drawable.pop));
        sliderItems.add(new BannerModel(R.drawable.pop));

        viewPager2.setAdapter(new SliderBannerAdapter(sliderItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(autoSlider);
                handler.postDelayed(autoSlider, 3000);
            }
        });
    }

    private Runnable autoSlider = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    private void loadListTopMusicRecently() {
        topMusicRecentlyModels = new ArrayList<>();
        topMusicRecentlyModels.add(new TopMusicRecentlyModel(0, 1, R.drawable.rock, "Nice For What", "Avinci John"));
        topMusicRecentlyModels.add(new TopMusicRecentlyModel(1, 2, R.drawable.rock, "Nice For What", "Avinci John"));
        topMusicRecentlyModels.add(new TopMusicRecentlyModel(2, 3, R.drawable.rock, "Nice For What", "Avinci John"));
        topMusicRecentlyModels.add(new TopMusicRecentlyModel(3, 4, R.drawable.rock, "Nice For What", "Avinci John"));
        topMusicRecentlyModels.add(new TopMusicRecentlyModel(4, 5, R.drawable.rock, "Nice For What", "Avinci John"));
        topMusicRecentlyModels.add(new TopMusicRecentlyModel(5, 6, R.drawable.rock, "Nice For What", "Avinci John"));
        topMusicRecentlyModels.add(new TopMusicRecentlyModel(6, 7, R.drawable.rock, "Nice For What", "Avinci John"));
        topMusicRecentlyModels.add(new TopMusicRecentlyModel(7, 8, R.drawable.rock, "Nice For What", "Avinci John"));
        topMusicRecentlyModels.add(new TopMusicRecentlyModel(8, 9, R.drawable.rock, "Nice For What", "Avinci John"));
        topMusicRecentlyModels.add(new TopMusicRecentlyModel(9, 10, R.drawable.rock, "Nice For What", "Avinci John"));

        adapterRecentlyTopMusic = new AdapterRecentlyTopMusic(getActivity(), topMusicRecentlyModels);
        rcvTopMusicRecently.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rcvTopMusicRecently.setAdapter(adapterRecentlyTopMusic);

    }


    private void rcvTheme() {
        rcv_category.setHasFixedSize(true);
        rcv_category.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        //service.getAllTheme().enqueue(getAllTheme);
    }

    private void rcvRecommend() {
        rcv_recommend.setHasFixedSize(true);
        rcv_recommend.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        service.getAllProduct().enqueue(getAllCB);
//        service.getAllRecommends().enqueue(getAllRecommendCB);

    }

    private void rcvAlbum() {
        rcv_album.setHasFixedSize(true);
        rcv_album.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        service.getAllAlbum().enqueue(getAllAlbumCB);

    }

    Callback<ArrayList<Product>> getAllCB = new Callback<ArrayList<Product>>() {
        @Override
        public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
            if (response.isSuccessful()){
                product_data = response.body();
//                adapter_product = new Adapter_Product(getActivity(), product_data);
                adapter_product2 = new Adapter_Product2(getActivity(), product_data);
                rcv_recommend.setAdapter(adapter_product2);
            }else {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
        }
    };

    Callback<ArrayList<Theme>> getAllTheme = new Callback<ArrayList<Theme>>() {
        @Override
        public void onResponse(Call<ArrayList<Theme>> call, Response<ArrayList<Theme>> response) {
            if (response.isSuccessful()){
                theme_data = response.body();
                adapter_theme = new Adapter_Theme(getActivity(), theme_data);
                rcv_category.setAdapter(adapter_theme);
            }else {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Theme>> call, Throwable t) {

        }
    };

    Callback<ArrayList<Album>> getAllAlbumCB = new Callback<ArrayList<Album>>() {
        @Override
        public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
            if (response.isSuccessful()){
                album_data = response.body();
                adapter_album = new Adapter_Album(getActivity(), album_data);
                rcv_album.setAdapter(adapter_album);
            }else {
                Log.e(">>>>>Error: ", String.valueOf(album_data));
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Album>> call, Throwable t) {

        }
    };

    Callback<ThemeCategory> getAllThemeCategory = new Callback<ThemeCategory>() {
        @Override
        public void onResponse(Call<ThemeCategory> call, Response<ThemeCategory> response) {
            if (response.isSuccessful()){
                ThemeCategory themeCategory = response.body();

                final ArrayList<Theme> themeArrayList = new ArrayList<>();
                themeArrayList.addAll(themeCategory.getThemes());

                final ArrayList<Category> categoryArrayList = new ArrayList<>();
                categoryArrayList.addAll(themeCategory.getCategories());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(190, 218);
                layoutParams.setMargins(10, 10, 0, 0);

                for(int i = 0; i < (themeArrayList.size()); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(20);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (themeArrayList.get(i).getTheme_image() != null) {
                        Glide.with(getActivity()).load(themeArrayList.get(i).getTheme_image()).into(imageView);
                    };
                    TextView textView = new TextView(getActivity());
                    textView.setText(themeArrayList.get(i).getTheme_name());
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    cardView.addView(textView);
                    linearLayout.addView(cardView);
                }

                for(int j = 0; j < (categoryArrayList.size()); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(20);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (categoryArrayList.get(j).getCategory_image() != null) {
                        Glide.with(getActivity()).load(categoryArrayList.get(j).getCategory_image()).into(imageView);
                    };
                    TextView textView = new TextView(getActivity());
                    textView.setText(categoryArrayList.get(j).getCategory_image());
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    cardView.addView(textView);
                    linearLayout.addView(cardView);
                }

                horizontalScrollView.addView(linearLayout);


            }else {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ThemeCategory> call, Throwable t) {

        }
    };

    @Override
    public void onResume() {
        super.onResume();
//        reloadscreen();
    }

    private void reloadscreen(){
//        IRetrofitService service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
//        service.getAllRecommends().enqueue(getAllRecommendCB);
//        service.getAllProduct().enqueue(getAllCB);
//        service.getAllCategories().enqueue(getAllCategoryCB);
    }

    private void initView(View view) {
        viewPager2 = view.findViewById(R.id.viewPagerBannerSlider);
        rcvTopMusicRecently = view.findViewById(R.id.rcv_list_recently_music);
    }
}