package com.codepath.apps.basictwitter.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.models.Tweet;
import com.squareup.picasso.Picasso;

public class TweetDetailsActivity extends AppCompatActivity {

    Button reply;
    TextView name;
    TextView time;
    TextView body;
    ImageView avi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);
        name = (TextView) findViewById(R.id.tvProfileNameDetails) ;
        body = (TextView) findViewById(R.id.tvBodyDetails) ;
        time = (TextView) findViewById(R.id.tvTimeDetails) ;
        avi = (ImageView) findViewById(R.id.ivProfileAviDetails);
        reply = (Button) findViewById(R.id.btnReply);
        name.setText(getIntent().getStringExtra("name"));
        time.setText(Tweet.getRelativeTimeAgo(getIntent().getStringExtra("time")));
        body.setText(getIntent().getStringExtra("body"));
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("picUrl")).into(avi);

        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TweetDetailsActivity.this, ComposeTweetActivity.class);
                startActivity(i);
            }
        });
    }
}
