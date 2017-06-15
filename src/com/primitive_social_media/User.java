package com.primitive_social_media;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by apatters on 6/11/2017.
 */
public class User {
    public String name;
    public String password;
    public String location;
    public Date DOB;
    public String business;
    public String picture;

    public ArrayList<String> followers = new ArrayList<>();
    public ArrayList<Integer> followedPosts = new ArrayList<>();
    public ArrayList<Integer> ownPosts = new ArrayList<>();
    public ArrayList<Integer> messages = new ArrayList<>();

    public User(String name, String password, String location, String DOBString, String business, String picture){
        this(name, password, location, business, picture);

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy");
        try {
            this.DOB = formatter.parse(DOBString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public User(String name, String password, String location, Date DOB, String business, String picture){
        this(name, password, location, business, picture);
        this.DOB = DOB;
    }

    public User(String name, String password, String location, String business, String picture){
        this.name = name;
        this.password = password;
        this.location = location;
        this.business = business;
        this.picture = picture;
    }

    public String toJSON(){
        String dateStr = DOB.toString();
        return String.format("{\"name\":%s, \"location\":%s, \"DOB\":%s, \"business\":%s, \"picture\":%s}", name, location, dateStr, business, picture);
    }


    public static String toJSON(HashMap<String, User> users){
        String usersInner = "";

        Iterator<String> usernameItr = users.keySet().iterator();
        while(usernameItr.hasNext()){
            User currentUser = users.get(usernameItr.next());
            if(usernameItr.hasNext()){
                usersInner += String.format("%s, ", currentUser.toJSON());
            }else{
                usersInner += currentUser.toJSON();
            }
        }

        return String.format("[%s]", usersInner);
    }
}