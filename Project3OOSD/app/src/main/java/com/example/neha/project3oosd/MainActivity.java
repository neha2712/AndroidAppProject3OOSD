package com.example.neha.project3oosd;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity
{
    List<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();

    private String jsonResult;
    public String url = "http://192.168.134.1/androidapp/getjsonA.php";
    ListView listView;
    List<Map<String, String>> agents = new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        accessWebService();

    }
    private class JsonReadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params)
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);
            try {
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            try {
                while ((rLine = rd.readLine()) != null) {
                    answer.append(rLine);
                }
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "error ..." + e.toString(), Toast.LENGTH_LONG).show();
            }
            return answer;
        }

        @Override
        protected void onPostExecute(String result)
        {
            ListDrwaer();
        }
    }

    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
        task.execute(new String[]{url});
    }

    public void ListDrwaer() {
        try {
            JSONObject jsonResponse = new JSONObject(jsonResult.substring(jsonResult.indexOf("{"), jsonResult.lastIndexOf("}") + 1));

            //json has one array named "agencies" which has 2 objects
            JSONArray jsonMainNode = jsonResponse.optJSONArray("agencies");

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject c = jsonMainNode.getJSONObject(i);

                // creating new HashMap
               map = new HashMap<String, String>();

                // adding each child node to HashMap key => value pair
                map.put("AgencyId", c.getString("AgencyId"));
                map.put("AgncyAddress", c.getString("AgncyAddress"));
                map.put("AgncyCity", c.getString("AgncyCity"));
                map.put("AgncyProv", c.getString("AgncyProv"));
                map.put("AgncyPostal", c.getString("AgncyPostal"));
                map.put("AgncyCountry", c.getString("AgncyCountry"));
                map.put("AgncyPhone", c.getString("AgncyPhone"));
                map.put("AgncyFax", c.getString("AgncyFax"));

                // adding HashList to ArrayList
                MyArrList.add(map);

                // Getting adapter by passing json data ArrayList
                SimpleAdapter simpleAdapter;

                //adapter with string array,custom layout and id's of the textviews
                simpleAdapter = new SimpleAdapter(MainActivity.this, MyArrList, R.layout.activity_column, new String[]{"AgencyId", "AgncyAddress","AgncyCity","AgncyProv","AgncyPostal","AgncyCountry","AgncyPhone","AgncyFax"}, new int[]{R.id.AgencyId , R.id.AgncyAddress,R.id.AgncyCity,R.id.AgncyProv, R.id.AgncyPostal,R.id.AgncyCountry,R.id.AgncyPhone,R.id.AgncyFax});

                //bind the adapter with the listview
                listView.setAdapter(simpleAdapter);

                //on item click navigates to another activity
                listView.setOnItemClickListener(onListClick);


            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "error parsing..." + e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

              if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private AdapterView.OnItemClickListener onListClick=new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> parent,View view, int position, long id)
        {

            if (position == 0)
            {
                Intent intent1 = new Intent(MainActivity.this, Agent1.class);
                startActivity(intent1);
            }
            else  if (position == 1)
            {
                Intent intent2 = new Intent(MainActivity.this, Agent2.class);
                startActivity(intent2);
            }

        }
    };

}