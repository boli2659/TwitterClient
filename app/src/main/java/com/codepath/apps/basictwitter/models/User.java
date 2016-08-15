package com.codepath.apps.basictwitter.models;


import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String name;
    private Long id;
    private String screenName;
    private String aviUrl;
    private String tagline;
    private int followers;
    private int following;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getAviUrl() {
        return aviUrl;
    }


    public String getTagline() {
        return tagline;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public static User fromJSON(JSONObject json) {
        User user = new User();
        try {
            user.name = json.getString("name");
            user.id = json.getLong("id");
            user.screenName = json.getString("screen_name");
            user.aviUrl = json.getString("profile_image_url");
            user.tagline = json.getString("description");
            user.followers = json.getInt("followers_count");
            user.following = json.getInt("friends_count");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
