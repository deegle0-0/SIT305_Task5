package com.example.task5take3;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class newsFragment extends Fragment implements NewsAdapter.ItemClick, TopStoriesAdapter.ItemClick{

    private ArrayList<News> newsArrayList = new ArrayList<>(); //List of news items
    public newsFragment() {
        // Required empty public constructor
    }

    public static newsFragment newInstance() {
        newsFragment fragment = new newsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        //inflating the news fragment which contains all the actual data of news

        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view){
        RecyclerView newsRecycle = view.findViewById(R.id.newsRecycle);
        // Two different recyclers to setup
        RecyclerView topStoryRecycle = view.findViewById(R.id.topStoryRecycle);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        // Gird for bottom linear for top recycler
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        getNews(); // calling function to populate news list

        NewsAdapter newsAdapter;  // used to send data to recycle view
        TopStoriesAdapter topStoriesAdapter;

        newsAdapter = new NewsAdapter(newsArrayList,getActivity(),this); // adapter setup
        topStoriesAdapter = new TopStoriesAdapter(newsArrayList,getActivity(),this);

        newsRecycle.setAdapter(newsAdapter);
        newsRecycle.setLayoutManager(layoutManager);

        topStoryRecycle.setAdapter(topStoriesAdapter);
        topStoryRecycle.setLayoutManager(layoutManager2);
    }

    private void getNews() {
        // can add questions in here easily without any issues.

        String[] headers = new String[]{
                getString(R.string.head1),
                getString(R.string.head2),
                getString(R.string.head3),
                getString(R.string.head4),
                getString(R.string.head5),
                getString(R.string.head6),
                getString(R.string.head7),
                getString(R.string.head8),
                getString(R.string.head9),
                getString(R.string.head10)
        };
        String[] desc = new String[]{
                getString(R.string.desc1),
                getString(R.string.desc2),
                getString(R.string.desc3),
                getString(R.string.desc4),
                getString(R.string.desc5),
                getString(R.string.desc6),
                getString(R.string.desc7),
                getString(R.string.desc8),
                getString(R.string.desc9),
                getString(R.string.desc10)
        };

        int[] images = new int[]{
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
                R.drawable.f,
                R.drawable.g,
                R.drawable.h,
                R.drawable.i,
                R.drawable.j,
        };

        for(int i=0;i<headers.length;i++)
        {
            News news = new News(headers[i],desc[i],images[i]);
            newsArrayList.add(news);
        }

    }

    @Override
    public void onItemClick(News news) {

        // onclick function which tells the adapter which item i clicked
        Fragment fragment = DetailFragment.newInstance(news.getTitle(),news.getDescription(),
                news.getTitleImage());

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.frame_container,fragment).commit();
    }
}