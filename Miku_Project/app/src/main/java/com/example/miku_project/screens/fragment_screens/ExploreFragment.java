package com.example.miku_project.screens.fragment_screens;

import static com.example.miku_project.helper.Config.BASE_URL;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.miku_project.R;
import com.example.miku_project.adapters.AdapterRecentlyTopMusic;
import com.example.miku_project.adapters.Adapter_TopFiveMusic;
import com.example.miku_project.models.MusicModel;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;
import com.example.miku_project.screens.fragment_screens.home.banner.SliderBannerAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreFragment extends Fragment {

    private RecyclerView rcv_playlist;
    private AdapterRecentlyTopMusic adapter_topFiveMusic;
    private ArrayList<MusicModel> playlist_data;
    private IRetrofitService service;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        initView(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requestTopFiveMusic();
    }

    private void requestTopFiveMusic() {
        playlist_data = new ArrayList<>();

        service.getTopFiveMusicRecent().enqueue(new Callback<ArrayList<MusicModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MusicModel>> call, Response<ArrayList<MusicModel>> response) {
                if (response.isSuccessful()){
                    playlist_data = response.body();

                    adapter_topFiveMusic = new AdapterRecentlyTopMusic(getActivity(), playlist_data);
                    rcv_playlist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    rcv_playlist.setAdapter(adapter_topFiveMusic);

                }else {
                    System.out.println("Error >>>" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MusicModel>> call, Throwable t) {
                Log.e(getActivity().getPackageName(), t.getLocalizedMessage());
            }
        });
    }

    private void initView(View view) {
        rcv_playlist = view.findViewById(R.id.rcv_topFiveMusic);
    }
}