package com.codepath.apps.basictwitter.models;


import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String name;
    private Long id;
    private String screenName;
    private String aviUrl;

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


    public static User fromJSON(JSONObject json) {
        User user = new User();
        try {
            user.name = json.getString("name");
            user.id = json.getLong("id");
            user.screenName = json.getString("screen_name");
            user.aviUrl = json.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
