package com.csoft.wong.feedmenow;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;


public class URLHandler {
    private JSONHandler jsonHandler;
    private static final String TAG = "URLHandler";

    public URLHandler(){
        jsonHandler = new JSONHandler();
    }

    public HashMap<String, HashMap<String, String>> getResult(String url){
        String jsonstring = "";
        try {
            jsonstring = readContents(url);
        } catch (Exception e) {
            Log.v(TAG, e.toString());
        }
        return this.jsonHandler.parseJson(jsonstring);
    }

    public String readContents(String address) throws Exception
    {
        StringBuilder contents = new StringBuilder(2048);
        BufferedReader br = null;

        try
        {
            URL url = new URL(address);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while (line != null)
            {
                line = br.readLine();
                contents.append(line);
            }
        }
        finally
        {
            if (!(br == null)){
                br.close();
            }
        }

        return contents.toString();
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
