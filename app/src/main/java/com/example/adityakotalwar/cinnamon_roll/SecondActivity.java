package com.example.adityakotalwar.cinnamon_roll;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    TextView menuText;

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        menuText = (TextView) findViewById(R.id.menuText);
        Intent intent = getIntent();
        //     Toast.makeText(this,"Hello" + intent.getStringExtra("Dining Halls"),Toast.LENGTH_SHORT).show();
        DownloadTask task = new DownloadTask();

        if (intent.getStringExtra("Dining Halls") == "Hillenbrang") {
            menuText.setText((CharSequence) task.execute("http://api.hfs.purdue.edu/menus/v1/locations/<Hillenbrand>/" + getDateTime()));
        } else if (intent.getStringExtra("Dining Halls") == "Wiley") {
            menuText.setText((CharSequence) task.execute("http://api.hfs.purdue.edu/menus/v1/locations/<Wiley>/" + getDateTime()));
        } else if (intent.getStringExtra("Dining Halls") == "Ford") {
            menuText.setText((CharSequence) task.execute("http://api.hfs.purdue.edu/menus/v1/locations/<Ford>/" + getDateTime()));
        } else if (intent.getStringExtra("Dining Halls") == "Windsor") {
            menuText.setText((CharSequence) task.execute("http://api.hfs.purdue.edu/menus/v1/locations/<Windsor>/" + getDateTime()));
        } else if (intent.getStringExtra("Dining Halls") == "Earhart") {
            menuText.setText((CharSequence) task.execute("http://api.hfs.purdue.edu/menus/v1/locations/<Earhart>/" + getDateTime()));
        }

    }

    public class DownloadTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection connection = null;

            try {
                url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char store = (char) data;
                    result += store;

                    data = reader.read();
                }
                return result;

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(s);
                String time = jsonObject.getString("Dining Halls");

                JSONArray arr = new JSONArray(time);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject part = arr.getJSONObject(i);

                    String timings = part.getString("Timings");
                    String food = part.getString("Food Options");

                    Log.i("Timings",timings); // to see what prints out
                    Log.i("Food",food); // to see what prints out
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
