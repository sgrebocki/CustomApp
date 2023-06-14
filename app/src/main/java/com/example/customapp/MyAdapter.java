package com.example.customapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataClassList;

    public MyAdapter(Context context, List<DataClass> dataClassList) {
        this.context = context;
        this.dataClassList = dataClassList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataClassList.get(position).getDataImage()).into(holder.recyclerImage);
        holder.recyclerTitle.setText(dataClassList.get(position).getDataTitle());
        holder.recyclerDesc.setText(dataClassList.get(position).getDataDescription());

        holder.recyclerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image", dataClassList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Title", dataClassList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Description", dataClassList.get(holder.getAdapterPosition()).getDataDescription());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataClassList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recyclerImage;
    TextView recyclerTitle, recyclerDesc;
    CardView recyclerCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recyclerImage = itemView.findViewById(R.id.recyclerImage);
        recyclerTitle = itemView.findViewById(R.id.recyclerTitle);
        recyclerDesc = itemView.findViewById(R.id.recyclerDesc);
        recyclerCard = itemView.findViewById(R.id.recyclerCard);
    }

}
