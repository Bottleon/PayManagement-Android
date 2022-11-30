package com.example.myapplication.common.recycleview;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class ViewHolder extends RecyclerView.ViewHolder{
    public Button button;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        button = itemView.findViewById(R.id.store_button);
        button.setOnClickListener(view->{
            String txt = button.getText().toString();
            System.out.println(txt);
        });
    }
}
