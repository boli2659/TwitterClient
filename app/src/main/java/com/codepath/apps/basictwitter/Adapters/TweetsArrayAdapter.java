package com.codepath.apps.basictwitter.Adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.Activities.TimelineActivity;
import com.codepath.apps.basictwitter.Activities.TweetDetailsActivity;
import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

public class TweetsArrayAdapter extends RecyclerView.Adapter<TweetsArrayAdapter.ViewHolder>{

    private static List<Tweet> mTweets;
    private static Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tvName;
        public TextView tvTime;
        public TextView tvBody;
        public ImageView ivAvi;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTime = (TextView) itemView.findViewById(R.id.tvTimestamp);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            ivAvi = (ImageView) itemView.findViewById(R.id.ivProfilePicture);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            Tweet tweet = mTweets.get(position);
            Intent i = new Intent(mContext, TweetDetailsActivity.class);
            i.putExtra("name", tweet.getUser().getName());
            i.putExtra("body", tweet.getBody());
            i.putExtra("time", tweet.getTimestamp());
            i.putExtra("picUrl", tweet.getUser().getAviUrl());
            startActivity(new TweetDetailsActivity(), i, Bundle.EMPTY);
        }
    }
    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        mContext = context;
        mTweets = tweets;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public TweetsArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder v = new ViewHolder(tweetView);
        return v;
    }

    @Override
    public void onBindViewHolder(TweetsArrayAdapter.ViewHolder holder, int position) {
        Tweet tweet = mTweets.get(position);
        TextView tvName = holder.tvName;
        TextView time = holder.tvTime;
        TextView body = holder.tvBody;
        ImageView ivPic = holder.ivAvi;

        tvName.setText(tweet.getUser().getName());
        body.setText(tweet.getBody());
        ivPic.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(tweet.getUser().getAviUrl()).into(ivPic);
        time.setText(Tweet.getRelativeTimeAgo(tweet.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }

}
