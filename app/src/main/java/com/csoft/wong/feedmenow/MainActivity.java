package com.csoft.wong.feedmenow;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private LinearLayout mLayout;
    private Button add_ing;
    private  EditText edit;
    private int count;
    private int resID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = (LinearLayout) findViewById(R.id.ing_list);
        add_ing = (Button) findViewById(R.id.add_ing);
        edit = (EditText) findViewById(R.id.ing0);
        count = 0;

    }


    public void test(View v) {
        mLayout.addView(addText());
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
