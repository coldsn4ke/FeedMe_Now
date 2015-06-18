package com.csoft.wong.feedmenow;


import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class JSONHandler {
    private static final String TAG = "JSONHandler";

    public HashMap<String, HashMap<String, String>> parseJson(String jsonstring){

        HashMap<String, HashMap<String, String>> resultMap = new HashMap<String, HashMap<String, String>>();

        try {
            JSONArray jsonArr = new JSONArray(jsonstring);

            if (!jsonArr.getJSONObject(3).getString("result").equals("[]"))
            {
                JSONObject title = jsonArr.getJSONObject(0);
                JSONObject version = jsonArr.getJSONObject(1);
                JSONObject href = jsonArr.getJSONObject(2);
                JSONObject result = jsonArr.getJSONObject(3);

                HashMap header = new HashMap<String, String>();
                header.put("title", title.getString("title"));
                header.put("version", version.getString("version"));
                header.put("href", href.getString("href"));




                Iterator keys = result.keys();
                int resultID = 0;

                if (keys.hasNext()) {
                    while (keys.hasNext()) {
                        HashMap subResultMap = new HashMap<String, String>();
                        String key = (String) keys.next();

                        JSONObject subObj = result.getJSONObject(key);

                        String resultTitle = subObj.getString("title");
                        String resultHref = subObj.getString("href");
                        String ingredients = subObj.getString("ingredients");
                        String thumbnail = subObj.getString("thumbnail");

                        subResultMap.put("title", resultTitle);
                        subResultMap.put("resultHref", resultHref);
                        subResultMap.put("ingredients", ingredients);
                        subResultMap.put("thumbnail", thumbnail);

                        resultMap.put(Integer.toString(resultID), subResultMap);
                    }
                } else {
                    HashMap<String, String> errorMap = new HashMap<String, String>();
                    errorMap.put("error","Keine Resultate gefunden!");
                    resultMap.put("error",errorMap);
                }



                resultMap.put("header", header);
            };




            for (int i = 1; i < jsonArr.length(); i++){
                JSONObject jsonsubObj = jsonArr.getJSONObject(i);
            }



        } catch (JSONException e) {
            Log.v(TAG, e.toString());
        }

        return resultMap;
    }
}
