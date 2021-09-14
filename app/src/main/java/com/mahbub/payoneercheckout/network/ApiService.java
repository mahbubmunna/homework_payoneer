package com.mahbub.payoneercheckout.network;

import com.mahbub.payoneercheckout.model.ListResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("listresult.json")
    Call<ListResult> getPaymentMethods();

}
