package com.csoft.wong.feedmenow;


import java.util.HashMap;

public class APILoader implements APIBinder {
    URLHandler urlHandler;

    public APILoader(){
        this.urlHandler = new URLHandler();
    }

    public HashMap<String, HashMap<String, String>> getAll(String url)
    {
        HashMap<String, HashMap<String, String>> returnMap = new HashMap<String, HashMap<String, String>>();
        returnMap = (this.urlHandler.getResult(url));
        return returnMap;
    };


    public HashMap<String, String> getById()
    {
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("","");
        return map1;
    };






}
