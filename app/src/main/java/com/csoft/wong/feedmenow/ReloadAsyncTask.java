package com.csoft.wong.feedmenow;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;

public class ReloadAsyncTask extends AsyncTask<String,String,String> {

    private ImageView img;
    private String imgText;
    private ListView list;
    private TextView title;
    private String titleText;
    private ArrayList<String> searchArray;
    private int counter;
    private Context context;
    private APIBinder apiBinder;
    private HashMap<String, HashMap<String, String>> allResults;
    private ProgressDialog dialog;

    public ReloadAsyncTask(ImageView img, ListView list, TextView title, ArrayList<String> searchArray, int counter, Context context, ProgressDialog dialog){
        this.img = img;
        this.list = list;
        this.title = title;
        this.searchArray = searchArray;
        this.counter = counter;
        this.context = context;
        this.dialog = dialog;
        apiBinder = new APILoader();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    protected String doInBackground(String... params) {

        String recipeUrl = "http://api.bigoven.com/recipe/"
                + searchArray.get(counter) +
                "?api_key=dvx9vaCumPhsRn5nALtmp5wO196Av1f3";

        String xmlstring = apiBinder.readUrl(recipeUrl);

        return xmlstring;
    }

    protected void onPostExecute(String xmlstring) {
        allResults = apiBinder.parseXml(xmlstring);

        titleText = allResults.get(Integer.toString(0)).get("title");
        imgText = allResults.get(Integer.toString(0)).get("thumbnail");
        String[] ingredients = allResults.get(Integer.toString(0)).get("ingredients").split(", ");
        String[] noIng = new String[1];
        noIng[0] = "Not specified";


        title.setText(titleText);

        this.img.setImageResource(R.drawable.loading_image);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(imgText, img);


        ArrayAdapter ing_list;

        if ( ingredients.length == 0){
            ing_list = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,noIng);
        }else{
            ing_list = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,ingredients);
        }

        list.setAdapter(ing_list);
        dialog.dismiss();
    }
}
