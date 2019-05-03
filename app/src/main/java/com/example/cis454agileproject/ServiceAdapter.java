package com.example.cis454agileproject;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.lang.ref.WeakReference;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>{

    private ClickListener listener;
    private List<Service> serviceList;


    public ServiceAdapter(List<Service> serviceList, ClickListener listener){
        this.serviceList = serviceList;
        this.listener = listener;
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

        return new ServiceViewHolder(itemView, listener);
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView vPoster;
        protected TextView vTitle;
        protected TextView vPayment;
        protected TextView vLocation;
        protected Button vButton;
        protected WeakReference<ClickListener> listenerRef;

        public ServiceViewHolder(final View v, ClickListener listener){
            super(v);
            vPoster = (TextView) v.findViewById(R.id.card_poster);
            vTitle = (TextView) v.findViewById(R.id.card_title);
            vPayment = (TextView) v.findViewById(R.id.card_payment);
            vLocation = (TextView) v.findViewById(R.id.card_address);
            vButton = (Button) v.findViewById(R.id.card_accept_btn);

            v.setOnClickListener(this);
            vButton.setOnClickListener(this);
        }

        // onClick Listener for view
        @Override
        public void onClick(View v) {

            System.out.println(vButton.getId());

            if (v.getId() == vButton.getId()) {
                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }

    }





}
