package com.codepath.apps.basictwitter.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.TwitterApplication;
import com.codepath.apps.basictwitter.TwitterClient;
import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ComposeTweetActivity extends AppCompatActivity {

    private TwitterClient client;

    EditText etBody;
    ImageButton ibSend;
    TextView tvCharCount;
    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            int i = 140 - s.length();
            tvCharCount.setText(String.valueOf(i));
        }

        public void afterTextChanged(Editable s) {
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_tweet);

        tvCharCount = (TextView) findViewById(R.id.tvCharsRemaining);
        etBody = (EditText) findViewById(R.id.editText);
        ibSend = (ImageButton) findViewById(R.id.ibSend);
        Picasso.with(this).load(R.drawable.twitterblack).fit().into(ibSend);
        etBody.addTextChangedListener(mTextEditorWatcher);

        client = TwitterApplication.getRestClient();

        ibSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etBody.getText().toString();
                if (text.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No Text", Toast.LENGTH_SHORT).show();
                } else {
                    Tweet tweet = new Tweet();
                    tweet.setBody(text);

                    client.postTweet(new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            Intent data = new Intent();
                            setResult(RESULT_OK, data);
                            finish();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            Intent data = new Intent();
                            setResult(RESULT_OK, data);
                            finish();
                        }
                    }, tweet);
                }
            }
        });
    }
}
