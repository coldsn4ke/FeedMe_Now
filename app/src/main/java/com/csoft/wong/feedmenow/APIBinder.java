package com.csoft.wong.feedmenow;

import java.util.HashMap;

public interface APIBinder {
    public HashMap<String, HashMap<String, String>> parseJson(String jsonstring);
    public String readUrl(String url);
}
