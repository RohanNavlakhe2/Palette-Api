package com.example.paletteapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StoreImagesAdapter extends RecyclerView.Adapter
{
    private Context context;
    private List<Integer> storeImages;
    ImageView imageView;
    public StoreImagesAdapter(Context context, List<Integer> storeImages) {
        this.context = context;
        this.storeImages = storeImages;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.store_images_rec,parent,false);
        return new StoreImagesInner(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Glide.with(context).load(storeImages.get(position)).into(imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startPaletteEffectActivity=new Intent(context,PaletteEffect.class);
                startPaletteEffectActivity.putExtra("img",storeImages.get(position));
                context.startActivity(startPaletteEffectActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeImages.size();
    }

    private class StoreImagesInner extends RecyclerView.ViewHolder
    {

        StoreImagesInner(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
        }
    }
}
