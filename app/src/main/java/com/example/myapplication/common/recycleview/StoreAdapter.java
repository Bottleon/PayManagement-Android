package com.example.myapplication.common.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.hr.store.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.CustomViewHoler> {
    private List<Store> arrayList;

    public StoreAdapter(ArrayList<Store> stores){
        arrayList = stores;
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
        holder.itemView.setOnClickListener(view->{
            String name = holder.storeButton.getText().toString();
            System.out.println(name);
            Toast.makeText(view.getContext(), name, Toast.LENGTH_SHORT).show();
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
