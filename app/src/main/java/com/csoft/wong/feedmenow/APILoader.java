package com.csoft.wong.feedmenow;


import java.util.HashMap;

public class APILoader implements APIBinder {
    JSONHandler jsonHandler;
    URLHandler urlHandler;

    public APILoader(){
        this.urlHandler = new URLHandler();
    }

    public HashMap<String, HashMap<String, String>> getAll(String url)
    {
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("","");
        HashMap<String, HashMap<String, String>> map2 = new HashMap<String, HashMap<String, String>>();
        map2.put("", map1);
        return map2;
    };


    public HashMap<String, String> getById()
    {
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("","");
        return map1;
    };






}
