package com.primitive_social_media;

import java.util.Iterator;
import java.util.ArrayList;

/**
 * Created by apatters on 6/11/2017.
 */
public class Post{

    private String poster;
    private String content;

    public static String toJSON(ArrayList<Post> posts){
        String postsInner = "";

        Iterator<Post> postItr = posts.iterator();
        while(postItr.hasNext()){
            Post currentPost = postItr.next();
            if(postItr.hasNext()){
                postsInner += String.format("%s, ", currentPost.toJSON());
            }else{
                postsInner += currentPost.toJSON();
            }
        }

        return String.format("[%s]", postsInner);
    }

    public Post(String poster, String content){
        this.poster=poster;
        this.content=content;
    }

    public void clear(){
        poster="";
        content="";
    }

    public String toJSON(){
        return String.format("{\"poster\":\"%s\", \"content\":\"%s\"}",poster, content);
    }
}
