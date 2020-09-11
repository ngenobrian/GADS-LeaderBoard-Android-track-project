package com.app.gadsleaderboard;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Skills {
    public String name;
    public String score;
    public String country;
    public String badgeUrl;

    public static final String BASE_API_URL = "https://gadsapi.herokuapp.com/api/skilliq";

    public Skills(String name, String hours, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public static ArrayList<Skills> getSkills(){
        final String NAME = "name";
        final String COUNTRY = "country";
        final String BADGEURL = "badgeUrl";
        final String SCORE = "score";

        ArrayList<Skills> lbSkills = new ArrayList<Skills>();

        try {
            URL url = ApiUtil.buildUrl(BASE_API_URL);
            String json = ApiUtil.getJson(url);
            JSONObject jsonHours = new JSONObject(json);
             Skills gadshours = new Skills(jsonHours.getString(NAME), jsonHours.getString(SCORE), jsonHours.getString(COUNTRY), jsonHours.getString(BADGEURL) );
             lbSkills.add(gadshours);
        }
        catch (JSONException | IOException e){
            e.printStackTrace();
        }
        return lbSkills;
    }







}
