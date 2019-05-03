package com.example.cis454agileproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;

import java.util.List;

// An adapter for the recycler view containing all of the services
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>{

    private ClickListener listener;
    private List<Service> serviceList;

    // Constructor with list of services and listener
    public ServiceAdapter(List<Service> serviceList, ClickListener listener){
        this.serviceList = serviceList;
        this.listener = listener;
    }

    // Get number of services
    @Override
    public int getItemCount(){
        return serviceList.size();
    }

    // populate view with service data
    @Override
    public void onBindViewHolder(ServiceViewHolder serviceViewHolder, int i){
        Service service = serviceList.get(i);

        serviceViewHolder.vPoster.setText(service.getPoster());
        serviceViewHolder.vTitle.setText(service.getTitle());
        serviceViewHolder.vPayment.setText(Double.toString(service.getPayment()));
        serviceViewHolder.vLocation.setText(service.getLocation());

        serviceViewHolder.bind(service, listener);
    }

    // inflate card view xml for each service
    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_cardview, viewGroup, false);

        return new ServiceViewHolder(itemView);
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        // Variables for view elements
        protected TextView vPoster;
        protected TextView vTitle;
        protected TextView vPayment;
        protected TextView vLocation;
        protected Button vButton;

        // Create service view holder and fetch all UI elements
        public ServiceViewHolder(final View v){
            super(v);
            vPoster = (TextView) v.findViewById(R.id.card_poster);
            vTitle = (TextView) v.findViewById(R.id.card_title);
            vPayment = (TextView) v.findViewById(R.id.card_payment);
            vLocation = (TextView) v.findViewById(R.id.card_address);
            vButton = (Button) v.findViewById(R.id.card_accept_btn);
        }

        // bind the created service to a listener for on click events
        public void bind(final Service s, final ClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onPositionClicked(s);
                }
            });
        }
    }
}
