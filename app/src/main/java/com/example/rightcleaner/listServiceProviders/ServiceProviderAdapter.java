package com.example.rightcleaner.listServiceProviders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rightcleaner.ProfilePage;
import com.example.rightcleaner.R;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.entity.UserServiceProvider;
import com.example.rightcleaner.helper.SessionManagement;

import java.util.List;

public class ServiceProviderAdapter extends RecyclerView.Adapter<ServiceProviderViewHolder> {
    List<UserServiceProvider> serviceProviderList;
    SessionManagement sessionManagement;
    Context context;
    public ServiceProviderAdapter(Context context){
        this.context=context;
        RightCleanerDataBase rightCleanerDataBase=RightCleanerDataBase.getRightCleanerDataBase(context);
        sessionManagement=new SessionManagement(context);
        serviceProviderList=rightCleanerDataBase.userServiceProviderDAO().getByService(sessionManagement.getServiceChoice().get("service_choice").toString());


    }


    @NonNull
    @Override
    public ServiceProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ServiceProviderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceProviderViewHolder holder, int position) {
    UserServiceProvider provider=serviceProviderList.get(position);
    holder.thead.setText(provider.getUsername());
    holder.tService.setText(provider.getService());
    holder.tprice.setText(provider.getPrice());
    holder.details.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, ProfilePage.class);
            sessionManagement.setProfile(provider);
            context.startActivity(intent);

        }
    });
    }

    @Override
    public int getItemCount() {
        return serviceProviderList.size();
    }
}
