package com.example.miku_project.myRetrofit;

//import com.example.demo_01.model.AccessTokenManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private String BASE_URL = "https://archive-music.host/api/";

    private Retrofit buildRetrofit() {

//        Interceptor interceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                String access_token = AccessTokenManager.getInstance(null).getToken().getAccess_token();
//                Request request = chain.request().newBuilder()
//                        .addHeader("Authorization", "Bearer " + access_token)
//                        .build();
//                return chain.proceed(request);
//            }
//        };

//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.interceptors().add(interceptor);
//        OkHttpClient client = builder.build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public <T> T createSerVice(Class<T> service, String url){
        BASE_URL = url;
        return buildRetrofit().create(service);
    }
}
