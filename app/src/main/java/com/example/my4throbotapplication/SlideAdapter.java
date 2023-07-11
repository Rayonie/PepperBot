package com.example.my4throbotapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder> {

    private List<SlideItem> slideItems;
    private ViewPager2 viewPager2;

    SlideAdapter(List<SlideItem> slideItems, ViewPager2 viewPager2) {
        this.slideItems = slideItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )

        );
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {

        holder.setImage(slideItems.get(position));
        if(position == slideItems.size() -2){

            viewPager2.post(runnable);
        }

    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    class SlideViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;

        SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        void setImage(SlideItem slideItem){
            imageView.setImageResource(slideItem.getImage());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            slideItems.addAll(slideItems);
            notifyDataSetChanged();
        }
    };
}
