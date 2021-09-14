package com.mahbub.payoneercheckout.viewmodel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mahbub.payoneercheckout.model.ListResult;
import com.mahbub.payoneercheckout.network.ApiService;
import com.mahbub.payoneercheckout.network.RetrofitInstance;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentListViewModel extends ViewModel {

    private final MutableLiveData<ListResult> paymentMethodsList;
    private final static String API_EXCEPTION = "api exception";
    public PaymentListViewModel() {
        paymentMethodsList = new MutableLiveData<>();

    }

    public boolean checkConnection(Context context){
        ConnectivityManager connection =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connection.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connection.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifi != null && wifi.isConnected()) ||
                (mobile !=null && mobile.isConnected())) {
            return true;
        } else {
            return false;
        }

    }

    public void fetchPaymentData() {
        ApiService apiService =
                RetrofitInstance.getClientInstance().create(ApiService.class);
        Call<ListResult> getPaymentMethods = apiService.getPaymentMethods();
        getPaymentMethods.enqueue(new Callback<ListResult>() {
            @Override
            public void onResponse(Call<ListResult> call, Response<ListResult> response) {
                    if (response.isSuccessful()) {
                        paymentMethodsList.postValue(response.body());
                    } else {
                        try {
                            Log.e(API_EXCEPTION, "Error Response: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            }

            @Override
            public void onFailure(Call<ListResult> call, Throwable t) {
                paymentMethodsList.postValue(null);
            }
        });
    }

    public MutableLiveData<ListResult> getPaymentMethodsList() {
        return  paymentMethodsList;
    }
}
