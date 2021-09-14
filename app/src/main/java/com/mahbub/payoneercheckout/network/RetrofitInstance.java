package com.mahbub.payoneercheckout.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static final String BASE_URL = "https://raw.githubusercontent.com/optile/checkout-android/develop/shared-test/lists/";

    private static Retrofit retrofit;

    public static Retrofit getClientInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
