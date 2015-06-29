package com.csoft.wong.feedmenow;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

public class readSearchURLAsyncTask extends AsyncTask<String, String, String>{
    private Intent intent;
    private Context context;
    private APIBinder apiBinder;
    private ArrayList ing_list;
    private ArrayList<String> searchArray;
    private ProgressDialog dialog;

    public readSearchURLAsyncTask(Intent intent, Context context, ArrayList ing_list, ProgressDialog dialog){
        this.context = context;
        this.intent = intent;
        this.apiBinder = new APILoader();
        this.ing_list = ing_list;
        this.dialog = dialog;
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

        if (searchArray.get(0) != "error") {
            String recipeUrl = "http://api.bigoven.com/recipe/"
                    + searchArray.get(0) +
                    "?api_key=dvx9vaCumPhsRn5nALtmp5wO196Av1f3";
            return recipeUrl;
        } else {
            return "error";
        }
    }

    protected void onPostExecute(String recipeUrl) {
        dialog.dismiss();
        if (recipeUrl != "error") {
            dialog.show();
            readURLAsyncTask readURLAT = new readURLAsyncTask(this.intent, this.context, this.searchArray, dialog);
            readURLAT.execute(recipeUrl);
        } else {
            new AlertDialog.Builder(context)
                    .setTitle("Failure")
                    .setMessage("We did not find what you were looking for...")
                    /*.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })*/
                    .show();
        }
    }
}


