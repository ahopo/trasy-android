package com.example.matt.trasy;

import android.app.ProgressDialog;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListofPoliceHotline extends ActionBarActivity {
    private ListView ls;
    private static final String location_name = "location_name";
    private static final String name = "name";
    private static final String number = "number";
    private static final String id = "id";
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> ClientList;
    JSONParser JSONConnect = new JSONParser();
    JSONArray hotlne_number=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_police_hotline);
        ConnectXML();
        Load_list();
        StrictMode.ThreadPolicy strict = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(strict);
        ClientList = new ArrayList<HashMap<String, String>>();
    }

    private  void ConnectXML()    {
        ls=(ListView)findViewById(R.id.lst_police_hotline);
    }
    private void Load_list()
    {
        List<String> list=new ArrayList<String>();
        list.add("123455 sample");
        list.add("5234545234 sample");
        list.add("998234 sample");
        list.add("8888843 sample");
        list.add("881231 sample");
        list.add("111233 sample");
        list.add("Ayus na!!");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        ls.setAdapter(adapter);

    }


}
