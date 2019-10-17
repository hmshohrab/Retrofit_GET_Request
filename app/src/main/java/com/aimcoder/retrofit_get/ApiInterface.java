package com.aimcoder.retrofit_get;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author SHOHRAB
 * @date 17-Oct-19
 */
public interface ApiInterface {

    @GET("/json")
    Call<ServerResponse> getMyIp();
}
