package com.codepath.apps.basictwitter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.basictwitter.Activities.ComposeTweetActivity;
import com.codepath.apps.basictwitter.Activities.TimelineActivity;
import com.codepath.apps.basictwitter.Adapters.TweetsArrayAdapter;
import com.codepath.apps.basictwitter.EndlessRecyclerViewScrollListener;
import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.TwitterApplication;
import com.codepath.apps.basictwitter.TwitterClient;
import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class TweetsListFragment extends Fragment {

    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter mAdapter;
    protected RecyclerView lvTweets;
    protected LinearLayoutManager layoutManager;
    protected SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);
        lvTweets = (RecyclerView) v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(mAdapter);
        lvTweets.setLayoutManager(layoutManager);

        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tweets = new ArrayList<>();
        mAdapter = new TweetsArrayAdapter(getActivity(), tweets);
        layoutManager = new LinearLayoutManager(getActivity());

    }

    public void addAll(List<Tweet> t) {
        tweets.clear();
        tweets.addAll(t);
        mAdapter.notifyDataSetChanged();
        //mAdapter.notify();
    }


}
