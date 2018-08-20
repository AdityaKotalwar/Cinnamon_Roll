package com.example.adityakotalwar.cinnamon_roll;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView LV = (ListView) findViewById(R.id.myListView);

        final ArrayList<String>  diningHalls = new ArrayList<String>();
        diningHalls.add("Hilly");
        diningHalls.add("Wiley");
        diningHalls.add("Ford");
        diningHalls.add("Windsor");
        diningHalls.add("Earhart");

        // make an array adaptor

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,diningHalls);

        //call the array adaptor
        LV.setAdapter(arrayAdapter);

        // add functionality to the buttons
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //   Log.i("Item clicked",diningHalls.get(position));

                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("Dining Halls",diningHalls.get(position));
                startActivity(intent);

            }
        });


    }

}
