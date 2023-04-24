package com.example.task5take3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    ArrayList<News> newsArrayList; //list of news

    private ItemClick clickListener; // for click able event
    Context context;


    public NewsAdapter(ArrayList<News> newsArrayList, Context context, ItemClick clickListener) {
        this.newsArrayList = newsArrayList;
        this.clickListener = clickListener;
        this.context = context;
    }


    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.news_layout, parent, false);
        return new MyViewHolder(view); // returning the class mentioned below
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News news = newsArrayList.get(position);

        holder.title.setText(news.getTitle());
        holder.imageView.setImageResource(news.titleImage);


        // holder to setup the items in recycler view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(newsArrayList.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.newsimage);
            title = itemView.findViewById(R.id.newstitle);
        }

        //setup the data in the blueprint layout we created

    }

    public interface ItemClick{
        public void onItemClick(News news);
    }
}