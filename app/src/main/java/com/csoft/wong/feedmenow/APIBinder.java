package com.csoft.wong.feedmenow;

import java.util.HashMap;
import java.util.Vector;

public interface APIBinder {
    public HashMap<String, HashMap<String, String>> parseJson(String jsonstring);
    public HashMap<String, HashMap<String, String>> parseXml(String xmlstring);
    public Vector<String> parseSearchXml(String xmlstring);
    public String readUrl(String url);
}
