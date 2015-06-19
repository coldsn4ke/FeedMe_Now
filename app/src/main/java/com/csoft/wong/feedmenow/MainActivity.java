package com.csoft.wong.feedmenow;

import android.os.AsyncTask;
import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private RelativeLayout mLayout;
    private Button add_ing;
    private EditText edit;
    private int count;
    private int resID;
    private APIBinder apiBinder;
    private EditText ingredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = (RelativeLayout) findViewById(R.id.ing_list);
        add_ing = (Button) findViewById(R.id.add_ing);
        edit = (EditText) findViewById(R.id.ing0);
        count = 0;
    }


    public void addEdit(View v) {
        mLayout.addView(addText());
        RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lparams.addRule(RelativeLayout.BELOW, resID + count);
        add_ing.setLayoutParams(lparams);
    }

    public void search(View v){
        ArrayList ing_list = new ArrayList();
        for (int i = 0; i < mLayout.getChildCount(); i++) {
            if (mLayout.getChildAt(i) instanceof EditText) {
                ingredient = (EditText) mLayout.getChildAt(i);
                ing_list.add(ingredient.getText().toString());
            }
        }


        String url = "http://www.recipepuppy.com/api/?i=";
        for (int i = 0; i < ing_list.size(); i++){
            if (i == 0) {
                url += ing_list.get(i).toString();
            } else {
                url += "," + ing_list.get(i).toString();
            }
        }

        new AsyncTask<String, String, String>(){
            @Override
            protected String doInBackground(String... url) {
                APIBinder apiBinder = new APILoader();
                String jsonstring = apiBinder.readUrl(url[0]);
                return jsonstring;
            }

            protected void onPostExecute(String jsonstring) {
                APIBinder apiBinder = new APILoader();
                HashMap<String, HashMap<String, String>> allResults = apiBinder.parseJson(jsonstring);

                /*ArrayList ing_list = new ArrayList();
                for (int i = 0; i < allResults.size()-1;i++){
                    ing_list.add(allResults.get(Integer.toString(i)).get("title"));
                }*/


                Intent intent;
                intent = new Intent(getApplicationContext(), Recipe_Activity.class);
                //intent.putStringArrayListExtra("ingredients", ing_list);
                intent.putExtra("title", allResults.get(Integer.toString(0)).get("title"));
                intent.putExtra("ingredients", allResults.get(Integer.toString(0)).get("ingredients").split(","));
                intent.putExtra("thumbnail", allResults.get(Integer.toString(0)).get("thumbnail"));
                intent.putExtra("href", allResults.get(Integer.toString(0)).get("resultHref"));
                startActivity(intent);
            }

        }.execute(url);



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
        editText.setMaxLines(1);

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
