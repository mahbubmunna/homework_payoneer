package com.mahbub.payoneercheckout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.mahbub.payoneercheckout.adapter.PaymentListAdapter;
import com.mahbub.payoneercheckout.model.ApplicableItem;
import com.mahbub.payoneercheckout.viewmodel.PaymentListViewModel;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private List<ApplicableItem> paymentMethods;
    private PaymentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.rv_payments_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PaymentListAdapter(this, paymentMethods);
        recyclerView.setAdapter(adapter);

        PaymentListViewModel model = new ViewModelProvider(this).get(PaymentListViewModel.class);
        fetchPayments(model);
        model.getPaymentMethodsList().observe(this, result -> {
            paymentMethods = result.getNetworks().getApplicable();
            adapter.setPaymentMethods(paymentMethods);
        });

    }

    private void fetchPayments(PaymentListViewModel model) {
        boolean isConnected = model.checkConnection(this);
        if (isConnected) {
            model.fetchPaymentData();
        } else {
            Toast.makeText(this,
                    "Check your internet and then open the again",
                    Toast.LENGTH_LONG).show();
        }
    }
}