package com.example.myapplication.common.recycleview;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<String> localDataSet = new ArrayList<String>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_textView;
        private TextView phone_textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_textView = itemView.findViewById(R.id.name_textView);
            phone_textView = itemView.findViewById(R.id.phone_textView);
        }
        public TextView getTextView() {
            return name_textView;
        }
    }

    public CustomAdapter (ArrayList<String> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stafflist_recyclerview_item, parent, false);
        CustomAdapter.ViewHolder viewHolder = new CustomAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        String text = localDataSet.get(position);
        holder.name_textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
