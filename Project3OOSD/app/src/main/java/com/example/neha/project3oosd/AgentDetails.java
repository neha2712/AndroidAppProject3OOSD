package com.example.neha.project3oosd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AgentDetails extends AppCompatActivity {

    private TextView a_id;
    private TextView a_fname;
    private TextView a_lname;
    private TextView a_phone;
    private TextView a_email;
    private TextView a_position;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_details);


        Intent intent = getIntent();

        id = intent.getStringExtra(Config.AGT_ID);

        a_id = (TextView) findViewById(R.id.a_id);
        a_fname = (TextView) findViewById(R.id.a_fname);
        a_lname = (TextView) findViewById(R.id.a_lname);
        a_phone = (TextView) findViewById(R.id.a_phone);
        a_email = (TextView) findViewById(R.id.a_email);
        a_position = (TextView) findViewById(R.id.a_position);

        a_id.setText(id);
        a_fname.setText(intent.getStringExtra(Config.TAG_FNAME));
        a_lname.setText(intent.getStringExtra(Config.TAG_LNAME));
        a_phone.setText(intent.getStringExtra(Config.TAG_PHONE));
        a_email.setText(intent.getStringExtra(Config.TAG_EMAIL));
        a_position.setText(intent.getStringExtra(Config.TAG_POSITION));


        getAgent();
    }

    private void getAgent(){
        class GetAgent extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AgentDetails.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showAgent(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_AGT,id);
                return s;
            }
        }
        GetAgent ga = new GetAgent();
        ga.execute();
    }

    private void showAgent(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String fname = c.getString(Config.TAG_FNAME);
            String lname = c.getString(Config.TAG_LNAME);
            String phone = c.getString(Config.TAG_PHONE);
            String email = c.getString(Config.TAG_EMAIL);
            String position = c.getString(Config.TAG_POSITION);


            a_fname.setText(fname);
            a_lname.setText(lname);
            a_phone.setText(phone);
            a_email.setText(email);
            a_position.setText(position);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
