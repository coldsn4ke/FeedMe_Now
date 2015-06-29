package com.csoft.wong.feedmenow;


import java.util.HashMap;
import java.util.Vector;

public class APILoader implements APIBinder {
    URLHandler urlHandler;
    JSONHandler jsonHandler;
    XMLHandler xmlHandler;

    public APILoader(){
        this.urlHandler = new URLHandler();
        this.jsonHandler = new JSONHandler();
        this.xmlHandler = new XMLHandler();
    }

    public String readUrl(String url){
        return this.urlHandler.readUrl(url);
    }

    public HashMap<String, HashMap<String, String>> parseJson(String jsonstring){
        return this.jsonHandler.parseJson(jsonstring);
    }

    public HashMap<String, HashMap<String, String>> parseXml(String xmlstring){
        return this.xmlHandler.parseXml(xmlstring);
    }

    public Vector<String> parseSearchXml(String xmlstring){
        return this.xmlHandler.parseSearchXml(xmlstring);
    }

}
