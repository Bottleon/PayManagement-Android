package com.example.myapplication.common.recycleview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CategoryActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.MarketlistActivity;
import com.example.myapplication.QRcheckActivity;
import com.example.myapplication.R;
import com.example.myapplication.hr.store.model.Store;
import com.example.myapplication.hr.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.CustomViewHoler> {
    private List<Store> arrayList;
    private User user;
    private Context context;
    public StoreAdapter(ArrayList<Store> stores, User user,Context context){
        this.arrayList = stores;
        this.user = user;
        this.context = context;
    }
    @NonNull
    @Override
    public StoreAdapter.CustomViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list,parent,false);
        CustomViewHoler holder = new CustomViewHoler(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAdapter.CustomViewHoler holder, int position) {
        holder.storeButton.setText(arrayList.get(position).getName());
        holder.itemView.setTag(position);
        holder.storeButton.setOnClickListener(view->{
            Store store = arrayList.get(holder.getAdapterPosition());
            Intent intent;
            System.out.println(user.getId());
            System.out.println(user.getAuthType());
            System.out.println(user.getName());
            if(user.getAuthType().equals("근로자")){
                intent = new Intent(context,QRcheckActivity.class);
            }else{
                intent = new Intent(context, CategoryActivity.class);
            }
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList!=null?arrayList.size():0;
    }

    public class CustomViewHoler extends RecyclerView.ViewHolder {
        protected AppCompatButton storeButton;

        public CustomViewHoler(@NonNull View itemView) {
            super(itemView);
            storeButton = (AppCompatButton) itemView.findViewById(R.id.store_button);
        }
    }
}
