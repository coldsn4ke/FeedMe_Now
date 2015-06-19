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
    private static final String TAG = "URLHandler";

    public URLHandler(){}

    public String readUrl(String url){
        String urlResult = "";
        try {
            urlResult = readContents(url);
        } catch (Exception e) {
            Log.v(TAG, e.toString());
        }
        return urlResult;
    }

    private String readContents(String address) throws Exception
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
}
