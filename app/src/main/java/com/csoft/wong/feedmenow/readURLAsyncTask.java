package com.csoft.wong.feedmenow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;


public class readURLAsyncTask extends AsyncTask<String, String, String> {

    private Intent intent;
    private Context context;
    private ArrayList<String> searchArray;
    private ProgressDialog dialog;

    public readURLAsyncTask(Intent intent, Context context, ArrayList<String> searchArray, ProgressDialog dialog){
        this.context = context;
        this.intent = intent;
        this.searchArray = searchArray;
        this.dialog = dialog;
    }

    @Override
    protected String doInBackground(String... url) {
        APIBinder apiBinder = new APILoader();
        String xmlstring = apiBinder.readUrl(url[0]);
        return xmlstring;
    }

    protected void onPostExecute(String xmlstring) {
        APIBinder apiBinder = new APILoader();
        HashMap<String, HashMap<String, String>> allResults = apiBinder.parseXml(xmlstring);

                /*ArrayList ing_list = new ArrayList();
                for (int i = 0; i < allResults.size()-1;i++){
                    ing_list.add(allResults.get(Integer.toString(i)).get("title"));
                }*/

        //intent.putStringArrayListExtra("ingredients", ing_list);
        intent.putExtra("title", allResults.get(Integer.toString(0)).get("title"));
        intent.putExtra("ingredients", allResults.get(Integer.toString(0)).get("ingredients").split(", "));
        intent.putExtra("thumbnail", allResults.get(Integer.toString(0)).get("thumbnail"));
        intent.putExtra("href", allResults.get(Integer.toString(0)).get("resultHref"));
        intent.putStringArrayListExtra("RecipeIDs", searchArray);
        context.startActivity(intent);
        dialog.dismiss();
        //((Activity)context).finish();
    }
}
