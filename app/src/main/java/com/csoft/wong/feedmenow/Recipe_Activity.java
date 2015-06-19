package com.csoft.wong.feedmenow;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;



import java.util.ArrayList;


public class Recipe_Activity extends ActionBarActivity {

    private RelativeLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        setContentView(R.layout.activity_recipe_);
        ListView list = (ListView) findViewById(R.id.recipe_list);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String[] ingredients = intent.getStringArrayExtra("ingredients");
        String thumbnail = intent.getStringExtra("thumbnail");
        String href = intent.getStringExtra("href");
        TextView titleView = (TextView) findViewById(R.id.recipe_name);
        titleView.setText(title);
        ImageView thumbnailView = (ImageView) findViewById(R.id.recipe_img);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(thumbnail, thumbnailView);

        ArrayAdapter ing_list = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ingredients);
        list.setAdapter(ing_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe_, menu);
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
