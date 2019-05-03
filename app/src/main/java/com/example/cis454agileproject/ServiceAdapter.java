package com.example.cis454agileproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;


import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {
    private List<Service> serviceList;

    public ServiceAdapter(List<Service> serviceList){
        this.serviceList = serviceList;
    }

    @Override
    public int getItemCount(){
        return serviceList.size();
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder serviceViewHolder, int i){
        Service service = serviceList.get(i);

        serviceViewHolder.vPoster.setText(service.getPoster());
        serviceViewHolder.vTitle.setText(service.getTitle());
        serviceViewHolder.vPayment.setText(Double.toString(service.getPayment()));
        serviceViewHolder.vLocation.setText(service.getLocation());
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_cardview, viewGroup, false);

        return new ServiceViewHolder(itemView);
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        protected TextView vPoster;
        protected TextView vTitle;
        protected TextView vPayment;
        protected TextView vLocation;

        public ServiceViewHolder(View v){
            super(v);
            vPoster = (TextView) v.findViewById(R.id.card_poster);
            vTitle = (TextView) v.findViewById(R.id.card_title);
            vPayment = (TextView) v.findViewById(R.id.card_payment);
            vLocation = (TextView) v.findViewById(R.id.card_address);
        }

    }



}
