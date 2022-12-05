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
import com.example.myapplication.common.exception.APIError;
import com.example.myapplication.common.message.AlertMessage;
import com.example.myapplication.common.retrofit.RetrofitClient;
import com.example.myapplication.common.token.TokenUtil;
import com.example.myapplication.hr.store.model.Store;
import com.example.myapplication.hr.user.model.User;
import com.example.myapplication.hr.userstore.api.UserStoreApi;
import com.example.myapplication.hr.userstore.model.UserStore;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            if(user.getAuthType().equals("근로자")){
                intent = new Intent(context,QRcheckActivity.class);
            }else{
                intent = new Intent(context, CategoryActivity.class);
            }
            UserStoreApi userStoreApi = RetrofitClient.getInstance().create(UserStoreApi.class);
            userStoreApi.getUserStore(TokenUtil.getAccessToken("act"),user.getId(), store.getId()).enqueue(new Callback<UserStore>() {
                @Override
                public void onResponse(Call<UserStore> call, Response<UserStore> response) {
                    if(response.isSuccessful()){
                        intent.putExtra("userStore", response.body());
                        context.startActivity(intent);
                    }else{
                        if (response.errorBody() != null) {
                            try {
                                Gson gson = new Gson();
                                APIError error = gson.fromJson(response.errorBody().string(),APIError.class);
                                AlertMessage.showMessage(context,error.message());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserStore> call, Throwable t) {
                    Toast.makeText(context,"server Error",Toast.LENGTH_SHORT).show();
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,t.getMessage());
                }
            });
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
