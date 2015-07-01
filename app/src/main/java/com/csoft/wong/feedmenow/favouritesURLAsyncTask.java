package com.csoft.wong.feedmenow;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by bwongc on 01.07.2015.
 */
public class favouritesURLAsyncTask extends AsyncTask<Void,Void,ArrayList<String>> {

    private Context context;

    private ListView favList;

    public favouritesURLAsyncTask(Context context, ListView favList){
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
        return strBuff;
    }

    protected void onPostExecute(ArrayList<String> strBuff) {

        String[] favString = new String[strBuff.size()];
        favString = strBuff.toArray(favString);

        ArrayAdapter favListAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1, strBuff);
        favList.setAdapter(favListAdapter);

    }


}
