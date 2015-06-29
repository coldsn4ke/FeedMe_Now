package com.csoft.wong.feedmenow;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

public class readSearchURLAsyncTask extends AsyncTask<String, String, String>{
    private Intent intent;
    private Context context;
    private APIBinder apiBinder;
    private ArrayList ing_list;
    private ArrayList<String> searchArray;

    public readSearchURLAsyncTask(Intent intent, Context context, ArrayList ing_list){
        this.context = context;
        this.intent = intent;
        this.apiBinder = new APILoader();
        this.ing_list = ing_list;
    }

    @Override
    protected String doInBackground(String... url) {
        String searchUrl = "http://api.bigoven.com/recipes?pg=1&rpp=25&title_kw=";
        for (int i = 0; i < ing_list.size(); i++){
            if (i == 0) {
                searchUrl += ing_list.get(i).toString();
            } else {
                searchUrl += "," + ing_list.get(i).toString();
            }
        }
        searchUrl += "&api_key=dvx9vaCumPhsRn5nALtmp5wO196Av1f3";

        String searchXml = apiBinder.readUrl(searchUrl);
        searchArray = apiBinder.parseSearchXml(searchXml);


        String recipeUrl = "http://api.bigoven.com/recipe/"
                + searchArray.get(0) +
                "?api_key=dvx9vaCumPhsRn5nALtmp5wO196Av1f3";

        return recipeUrl;
    }

    protected void onPostExecute(String recipeUrl) {
        readURLAsyncTask readURLAT = new readURLAsyncTask(this.intent, this.context, this.searchArray);
        readURLAT.execute(recipeUrl);
    }
}

