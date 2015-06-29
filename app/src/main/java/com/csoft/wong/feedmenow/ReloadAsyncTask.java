package com.csoft.wong.feedmenow;

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
    private ListView list;
    private TextView title;
    private ArrayList<String> searchArray;
    private int counter;
    private Context context;
    private APIBinder apiBinder;

    public ReloadAsyncTask(ImageView img, ListView list, TextView title, ArrayList<String> searchArray, int counter, Context context){
        this.img = img;
        this.list = list;
        this.title = title;
        this.searchArray = searchArray;
        this.counter = counter;
        this.context = context;
        apiBinder = new APILoader();
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
        HashMap<String, HashMap<String, String>> allResults = apiBinder.parseXml(xmlstring);

        String titleText = allResults.get(Integer.toString(0)).get("title");
        String imgText = allResults.get(Integer.toString(0)).get("thumbnail");
        String[] ingredients = allResults.get(Integer.toString(0)).get("ingredients").split(", ");

        title.setText(titleText);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        ImageLoader.getInstance().init(config);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(imgText, img);

        ArrayAdapter ing_list = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,ingredients);
        list.setAdapter(ing_list);
    }
}
