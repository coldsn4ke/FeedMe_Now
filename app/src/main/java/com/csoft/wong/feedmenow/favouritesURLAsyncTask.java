package com.csoft.wong.feedmenow;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class favouritesURLAsyncTask extends AsyncTask<Void,Void,ArrayList<String>> {

    private Favourites_Activity context;

    private ListView favList;

    public favouritesURLAsyncTask(Favourites_Activity context, ListView favList){
        this.context = context;
        this.favList = favList;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        ArrayList<String> strBuff = new ArrayList<String>();

        File file = new File("/sdcard/feedmenow/favourites.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");
                while (lineScanner.hasNext()) {
                    String part = lineScanner.next();
                    if(!part.equals(""))
                        strBuff.add(part);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //return strBuff;

        context.hrefList = new HashMap<String, String>();

        for (int i = 0; i < strBuff.size(); i++) {
            APIBinder apiBinder = new APILoader();
            String xmlstring = apiBinder.readUrl(
                    "http://api.bigoven.com/recipe/"+
                            strBuff.get(i)+
                            "?api_key=dvx9vaCumPhsRn5nALtmp5wO196Av1f3"
            );
            HashMap<String, HashMap<String, String>> allResults = apiBinder.parseXml(xmlstring);

            context.hrefList.put(
                    allResults.get(Integer.toString(0)).get("title"),
                    allResults.get(Integer.toString(0)).get("resultHref")
            );

        }

        ArrayList<String> titleList = new ArrayList<String>();
        Iterator it = context.hrefList.entrySet().iterator();
        while (it.hasNext()){
            HashMap.Entry pair = (HashMap.Entry)it.next();
            titleList.add(pair.getKey().toString());
        }

        return titleList;
    }

    protected void onPostExecute(ArrayList<String> strBuff) {

        ArrayAdapter favListAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1, strBuff);
        favList.setAdapter(favListAdapter);
        favList.setOnItemClickListener(context.myListClickedHandler);

    }


}
