package com.example.task5take3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.MyViewHolder>{
    ArrayList<News> newsArrayList; // same list
    Context context;
    private ItemClick clickListener; // making varible for us to call that we can use in fragment

    public TopStoriesAdapter(ArrayList<News> newsArrayList, Context context,ItemClick clickListener) {
        this.newsArrayList = newsArrayList;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public TopStoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topstories_layout,parent,false);

        return new TopStoriesAdapter.MyViewHolder(view); // returning the class mentioned below
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoriesAdapter.MyViewHolder holder, int position) {
        News news = newsArrayList.get(position);
        // we need the positon to setup the holders and for the click function

        holder.textView.setText(news.getTitle());
        holder.imageView.setImageResource(news.titleImage);

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
        TextView textView;

        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.topImage);
            textView = itemView.findViewById(R.id.textView2);

        }
    }

    public interface ItemClick{
        public void onItemClick(News news);
    }
}
