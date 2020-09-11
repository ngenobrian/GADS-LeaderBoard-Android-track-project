package com.app.gadsleaderboard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class LeaderBoard {
    public String name;
    public String hours_skilliq;
    public String country;
    public String badgeUrl;
    public String score;

    public static final String BASE_API_URL_HOURS = "https://gadsapi.herokuapp.com/api/hours";
    public static final String BASE_API_URL_SKILLS = "https://gadsapi.herokuapp.com/api/skilliq";

    private static String sLbname;
    private static String sLbhours;
    private static String sLbcountry;
    private static String sLbBadgeUrl;
    private  static  String sLScore;

    public LeaderBoard(String name, String hours_skilliq, String country, String badgeUrl) {
        this.name = name;
        this.hours_skilliq = hours_skilliq;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public static ArrayList<LeaderBoard> getHours(String result){
        final String NAME = "name";
        final String COUNTRY = "country";
        final String BADGEURL = "badgeUrl";
        final String HOURS = "hours";

        ArrayList<LeaderBoard> lbHours = new ArrayList<LeaderBoard>();

        try {
            JSONArray jsonHours = new JSONArray(result);
            JSONObject name = null;

            for(int i=0; i<jsonHours.length(); i++){
                name = jsonHours.getJSONObject(i);
                sLbname = name.getString(NAME);
                sLbhours = name.getString(HOURS);
                sLbcountry = name.getString(COUNTRY);
                sLbBadgeUrl = name.getString(BADGEURL);

                LeaderBoard gadshours = new LeaderBoard(sLbname, sLbhours, sLbcountry, sLbBadgeUrl );
                lbHours.add(gadshours);
            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return lbHours;
    }

    public static ArrayList<LeaderBoard> getDataFromJson(String result){
        final String NAME = "name";
        final String COUNTRY = "country";
        final String BADGEURL = "badgeUrl";
        final String HOURS = "hours";
        final String SCORE = "score";

        ArrayList<LeaderBoard> lbData = new ArrayList<LeaderBoard>();

        try {
            JSONArray jsonData = new JSONArray(result);
            JSONObject jsonArray = null;


            for(int i=0; i<jsonData.length(); i++){
                jsonArray = jsonData.getJSONObject(i);
                Iterator<String> keys = jsonArray.keys();
                String key = null;
                String hours_skills = null;
                while (keys.hasNext()){
                    key = keys.next();
                    if(key.contains(HOURS)){
                        hours_skills = jsonArray.getString(HOURS) + " "+ "learning hours";

                    }
                    else if(key.contains(SCORE)){
                        hours_skills = jsonArray.getString(SCORE) + " "+"skill IQ Score";
                    }
                }
                sLbname = jsonArray.getString(NAME);
                sLbcountry = jsonArray.getString(COUNTRY);
                sLbBadgeUrl = jsonArray.getString(BADGEURL);
                LeaderBoard gadshours = new LeaderBoard(sLbname, hours_skills, sLbcountry, sLbBadgeUrl);
                lbData.add(gadshours);
            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return lbData;
    }







}
