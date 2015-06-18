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

    public JSONHandler(){};

    public HashMap<String, HashMap<String, String>> parseJson(String jsonstring){

        HashMap<String, HashMap<String, String>> resultMap = new HashMap<String, HashMap<String, String>>();

        try {
            JSONObject jsonObj = new JSONObject(jsonstring);

            //if (!jsonObj.getString("result").equals("[]"))
            //{

                HashMap header = new HashMap<String, String>();
                header.put("title", jsonObj.getString("title"));
                header.put("version", jsonObj.getString("version"));
                header.put("href", jsonObj.getString("href"));
                resultMap.put("header", header);

                JSONArray resultArr = jsonObj.getJSONArray("results");


                for (int i=0; i < resultArr.length(); i++){
                    HashMap subResultMap = new HashMap<String, String>();
                    subResultMap.put("title", resultArr.getJSONObject(i).get("title"));
                    subResultMap.put("resultHref", resultArr.getJSONObject(i).get("href"));
                    subResultMap.put("ingredients", resultArr.getJSONObject(i).get("ingredients"));
                    subResultMap.put("thumbnail", resultArr.getJSONObject(i).get("thumbnail"));
                    resultMap.put(Integer.toString(i), subResultMap);
                }

                /*Iterator keys = resultArr.keys();
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

                        subResultMap.put("title", key);
                        subResultMap.put("resultHref", resultHref);
                        subResultMap.put("ingredients", ingredients);
                        subResultMap.put("thumbnail", thumbnail);

                        resultMap.put(Integer.toString(resultID), subResultMap);
                    }
                } else {
                    HashMap<String, String> errorMap = new HashMap<String, String>();
                    errorMap.put("error","Keine Resultate gefunden!");
                    resultMap.put("error",errorMap);
                }*/





            //}
            ;



        } catch (JSONException e) {
            Log.v(TAG, e.toString());
        }

        return resultMap;
    }
}
