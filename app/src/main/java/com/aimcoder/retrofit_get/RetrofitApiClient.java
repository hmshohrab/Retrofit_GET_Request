package com.aimcoder.retrofit_get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author SHOHRAB
 * @date 17-Oct-19
 */
public class RetrofitApiClient {
    private static final String BASE_URL =   "http://ifconfig.co";
    private static Retrofit mRetrofit = null;


private static Gson sGson = new GsonBuilder().setLenient().create();

    private RetrofitApiClient() {

    }

    public static Retrofit getClient(){
        if (mRetrofit==null){
            synchronized (RetrofitApiClient.class){
                if (mRetrofit==null){
                mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(sGson)).build();
                }
            }
        }
        return mRetrofit;
    }
}
