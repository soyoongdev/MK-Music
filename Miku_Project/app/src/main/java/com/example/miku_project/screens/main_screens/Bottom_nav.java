package com.example.miku_project.screens.main_screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.miku_project.R;
import com.example.miku_project.fragments.FavoriteFragment;
import com.example.miku_project.fragments.HomeFragment;
import com.example.miku_project.fragments.PlaylistFragment;
import com.example.miku_project.fragments.ProfileFragment;
import com.example.miku_project.fragments.SearchFragment;

public class Bottom_nav extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        android.graphics.drawable.Drawable background = Bottom_nav.this.getResources().getDrawable(R.color.bg_status_color);
//        getWindow().setBackgroundDrawable(background);

        bottomNavigation = findViewById(R.id.bottom_nav);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_playlist_play));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_favorite));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_search));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_person));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
                        fragment = new PlaylistFragment();
                        break;
                    case 2:
                        fragment = new FavoriteFragment();
                        break;
                    case 3:
                        fragment = new HomeFragment();
                        break;
                    case 4:
                        fragment = new SearchFragment();
                        break;
                    case 5:
                        fragment = new ProfileFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });
        //Set home fragment initially selected
        bottomNavigation.show(3, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });

    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.bottom_frame_layout, fragment)
                .setReorderingAllowed(true)
                .commit();
    }
}