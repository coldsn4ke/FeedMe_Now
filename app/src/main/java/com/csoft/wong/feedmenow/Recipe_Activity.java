package com.csoft.wong.feedmenow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;



import java.util.ArrayList;


public class Recipe_Activity extends ActionBarActivity {

    private String url;
    private ArrayList<String> searchArray;
    private RelativeLayout mLayout;
    private float x1,x2;
    static final int MIN_DISTANCE = 50;
    private int counter;
    private ListView list;
    private TextView titleView;
    private ImageView thumbnailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        counter = 1;

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        setContentView(R.layout.activity_recipe_);
        list = (ListView) findViewById(R.id.recipe_list);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String[] ingredients = intent.getStringArrayExtra("ingredients");
        String thumbnail = intent.getStringExtra("thumbnail");
        url = intent.getStringExtra("href");
        titleView = (TextView) findViewById(R.id.recipe_name);
        titleView.setText(title);
        thumbnailView = (ImageView) findViewById(R.id.recipe_img);


        this.thumbnailView.setImageResource(R.drawable.loading_image);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(thumbnail, thumbnailView);


        searchArray = intent.getStringArrayListExtra("RecipeIDs");

        ArrayAdapter ing_list = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ingredients);
        list.setAdapter(ing_list);
    }

    public void openLink(View v){

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

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

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Please wait...");
                        dialog.show();
                        new ReloadAsyncTask(thumbnailView,list,titleView,searchArray,counter,this,dialog).execute();
                        counter++;
                    }

                    // Right to left swipe action
                    else
                    {
                        Toast.makeText(this, "Right to Left swipe [Previous]", Toast.LENGTH_SHORT).show ();
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
