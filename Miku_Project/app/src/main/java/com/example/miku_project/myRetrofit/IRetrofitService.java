package com.example.miku_project.myRetrofit;

import com.example.miku_project.models.Album;
import com.example.miku_project.models.Category;
import com.example.miku_project.models.Favorite;
import com.example.miku_project.models.FavoriteCategory;
import com.example.miku_project.models.LoginResult;
import com.example.miku_project.models.MusicModel;
import com.example.miku_project.models.Playlist;
import com.example.miku_project.models.Product;
import com.example.miku_project.models.Recommend;
import com.example.miku_project.models.Response2pikModel;
import com.example.miku_project.models.ResponseModel;
import com.example.miku_project.models.Theme;
import com.example.miku_project.models.ThemeCategory;
import com.example.miku_project.models.User;
import com.example.miku_project.screens.fragment_screens.home.banner.BannerModel;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IRetrofitService {

    @POST("auth/login")
    Call<LoginResult> login(@Body User user);

    @POST("auth/signup")
    Call<ResponseModel> register(@Body User user);

    @POST("auth/password/forget_request")
    Call<ResponseModel> forgot_password(@Body User user);

    @POST("views/product_get_all.php")
    Call<ArrayList<Product>> getAllProduct();

    @GET("https://62f61000612c13062b4565e8.mockapi.io/api/bannerTopMusic/listBanners")
    Call<ArrayList<BannerModel>> getListBanner();

    @GET("https://62f61000612c13062b4565e8.mockapi.io/api/bannerTopMusic/listTopMusicRecent")
    Call<ArrayList<MusicModel>> getListTopMusicRecent();

    @GET("https://62f61000612c13062b4565e8.mockapi.io/api/bannerTopMusic/topFiveMusicAttention")
    Call<ArrayList<MusicModel>> getTopFiveMusicRecent();

    @Multipart
    @POST("/")
    Call<Response2pikModel> upload2pik(@Part MultipartBody.Part image);

    @POST("views/category_get_all.php")
    Call<ArrayList<Category>> getAllCategories();

    @POST("views/product_insert.php")
    Call<ResponseModel> insert(@Body Product product);

    @POST("views/product_get_by_id.php")
    Call<Product> getProductById(@Body Product product);

    @POST("views/product_update.php")
    Call<ResponseModel> update(@Body Product product);

    @POST("views/product_delete.php")
    Call<ResponseModel> delete(@Body Product product);

    @POST("views/favorite_get_all.php")
    Call<ArrayList<Favorite>> getAllFavorite();

    @POST("views/favorite_category_get_all.php")
    Call<ArrayList<FavoriteCategory>> getAllFavoriteCategory();

    @POST("views/favorite_insert.php")
    Call<ResponseModel> insertFavorite(@Body Favorite favorite);

    @POST("views/favorite_get_by_id.php")
    Call<Favorite> getFavoriteById(@Body Favorite favorite);

    @POST("views/favorite_update.php")
    Call<ResponseModel> updateFavorite(@Body Favorite favorite);

    @POST("views/favorite_delete.php")
    Call<ResponseModel> deleteFavorite(@Body Favorite favorite);

    @POST("views/recommend_get_all.php")
    Call<ArrayList<Recommend>> getAllRecommends();

    @POST("views/playlist_get_all.php")
    Call<ArrayList<Playlist>> getAllPlaylist();

    @POST("views/theme_category_get_all.php")
    Call<ThemeCategory> getThemeCategory();

    @POST("views/album_get_all.php")
    Call<ArrayList<Album>> getAllAlbum();

    @POST("views/theme_get_all.php")
    Call<ArrayList<Theme>> getAllTheme();

    @POST("views/category_get_all.php")
    Call<ArrayList<Category>> getCategory();

    @FormUrlEncoded
    @POST("views/product_list.php")
    Call<ArrayList<Product>> getProductList(@Field("recommend_id") String recommend_id);

    @FormUrlEncoded
    @POST("views/playlist_song.php")
    Call<ArrayList<Product>> getPlaylistsong(@Field("playlist_id") String playlist_id);

    @FormUrlEncoded
    @POST("views/product_search.php")
    Call<ArrayList<Product>> getSearch(@Field("keyname") String keyname);
}
