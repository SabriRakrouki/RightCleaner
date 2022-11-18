package com.example.rightcleaner.listServiceProviders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rightcleaner.R;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.entity.UserServiceProvider;

import java.util.List;

public class ServiceProviderAdapter extends RecyclerView.Adapter<ServiceProviderViewHolder> {
    List<UserServiceProvider> serviceProviderList;
    public ServiceProviderAdapter(Context context){
        RightCleanerDataBase rightCleanerDataBase=RightCleanerDataBase.getRightCleanerDataBase(context);
        serviceProviderList=rightCleanerDataBase.userServiceProviderDAO().getAll();
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
    holder.thead.setText(provider.getName()+" "+provider.getFamilyName());
    holder.tService.setText(provider.getService());
    holder.tprice.setText(provider.getPrice());
    holder.details.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //go details

        }
    });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
