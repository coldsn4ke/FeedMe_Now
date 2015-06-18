package com.csoft.wong.feedmenow;

import java.util.HashMap;

public interface APIBinder {
    public HashMap<String, HashMap<String, String>> getAll(String url);
    public HashMap<String, String> getById();
}
