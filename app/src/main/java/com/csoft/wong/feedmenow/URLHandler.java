package com.csoft.wong.feedmenow;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class URLHandler {
    private JSONHandler jsonHandler;
    private static final String TAG = "URLHandler";

    public URLHandler(){
        jsonHandler = new JSONHandler();
    }

    private void getResult(String url){

        new AsyncTask<String, String, String>(){

            @Override
            protected String doInBackground(String... urlResult) {
                String msg = "";


                //TODO May be more than one result
                HttpGet get = new HttpGet(urlResult[0]);

                DefaultHttpClient client = new DefaultHttpClient();

                try {
                    HttpResponse response = client.execute(get);

                    int code = response.getStatusLine().getStatusCode();

                    msg = EntityUtils.toString(response.getEntity());

                    Log.i(TAG, Integer.toString(code));

                } catch (Exception e) {
                    Log.v(TAG, e.toString());
                }
                //Log.v(TAG, "\nTHIS IS THE MESSAGE RETURNED: " + msg);
                return msg;

            }

            protected void onPostExecute(String result) {
                jsonHandler.parseJson(result);
            }
        }.execute(url);

    }
}
