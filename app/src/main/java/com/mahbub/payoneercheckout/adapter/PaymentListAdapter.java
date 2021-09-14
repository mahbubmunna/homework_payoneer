package com.mahbub.payoneercheckout.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mahbub.payoneercheckout.R;
import com.mahbub.payoneercheckout.model.ApplicableItem;
import com.mahbub.payoneercheckout.model.ListResult;

import java.util.List;

public class PaymentListAdapter extends RecyclerView.Adapter<PaymentListAdapter.PaymentViewHolder> {
    private List<ApplicableItem> paymentMethods;
    private final Context context;
    public  PaymentListAdapter(Context context, List<ApplicableItem> dataLists) {
        this.paymentMethods = dataLists;
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPaymentMethods(List<ApplicableItem> dataLists) {
        paymentMethods = dataLists;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PaymentListAdapter.PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_method_item, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull PaymentListAdapter.PaymentViewHolder holder, int position) {
        holder.textView.setText(paymentMethods.get(position).getLabel());
        Glide.with(context).load(paymentMethods.get(position)
                .getLinks()
                .getLogo())
                .apply(RequestOptions.noTransformation())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (paymentMethods != null) {
            return paymentMethods.size();
        }
        return 0;
    }

    public static class PaymentViewHolder extends RecyclerView.ViewHolder {
        final private TextView textView;
        final private ImageView imageView;
        public PaymentViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.payment_text_view);
            imageView = itemView.findViewById(R.id.payment_image_view);
        }


    }
}
