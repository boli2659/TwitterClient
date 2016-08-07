package com.codepath.apps.basictwitter.models;

import android.text.format.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Tweet {

    private String body;
    private long uid;
    private String timestamp;
    private User user;

    public String getBody() {
        return body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public long getUid() {
        return uid;
    }
    public User getUser() {
        return user;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static Tweet fromJson(JSONObject object) {
        Tweet tweet = new Tweet();

        try {
            tweet.body = object.getString("text");
            tweet.uid = object.getLong("id");
            tweet.timestamp = object.getString("created_at");
            tweet.user = User.fromJSON(object.getJSONObject("user"));

        } catch (JSONException e) {

            e.printStackTrace();
        }

        return tweet;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray json) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            try {
                JSONObject j = json.getJSONObject(i);
                Tweet tweet = fromJson(j);
                if (tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }

        }

        return tweets;
    }

    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
