package com.csoft.wong.feedmenow;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    private RelativeLayout mLayout;
    private Button add_ing;
    private  EditText edit;
    private int count;
    private int resID;
    private APIBinder apiBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = (RelativeLayout) findViewById(R.id.ing_list);
        add_ing = (Button) findViewById(R.id.add_ing);
        edit = (EditText) findViewById(R.id.ing0);
        count = 0;
        APIBinder apiBinder = new APILoader();



        String url = "http://www.recipepuppy.com/api/?i=onion,garlic&p=3";
        new AsyncTask<String, String, String>(){
            @Override
            protected String doInBackground(String... badi) {
                APIBinder apiBinder = new APILoader();
                HashMap<String, HashMap<String, String>> allResults = apiBinder.getAll("http://www.recipepuppy.com/api/?i=onion,garlic&p=3");
                try {
                    Log.v("allResults", allResults.get("header").get("title"));
                } catch (Exception e){
                    Log.v("allResults", "DIDN'T WORK!");
                }
                return allResults.get("header").get("title");
            }

            protected void onPostExecute(String result) {

            }

        }.execute(url);


    }


    public void test(View v) {
        mLayout.addView(addText());
        RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lparams.addRule(RelativeLayout.BELOW, resID + count);
        add_ing.setLayoutParams(lparams);
    }


    private EditText addText() {
        if(count==0){
            resID = getResources().getIdentifier("ing0", "id", getPackageName());
        }else {
            resID = edit.getId();
        }
        final EditText editText = new EditText(this);
        RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lparams.addRule(RelativeLayout.BELOW, resID + count);
        editText.setLayoutParams(lparams);
        count++;
        editText.setId(resID + count);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setHint("Ingredient");

        return editText;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
