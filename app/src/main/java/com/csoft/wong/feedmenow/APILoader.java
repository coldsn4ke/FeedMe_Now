package com.csoft.wong.feedmenow;


import java.util.HashMap;

public class APILoader implements APIBinder {
    URLHandler urlHandler;
    JSONHandler jsonHandler;

    public APILoader(){
        this.urlHandler = new URLHandler();
        this.jsonHandler = new JSONHandler();
    }

    public String readUrl(String url){
        return this.urlHandler.readUrl(url);
    }

    public HashMap<String, HashMap<String, String>> parseJson(String jsonstring){
        return this.jsonHandler.parseJson(jsonstring);
    }

}
