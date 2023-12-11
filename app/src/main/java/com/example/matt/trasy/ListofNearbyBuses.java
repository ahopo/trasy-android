package com.example.matt.trasy;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//lst_nearby_bus
public class ListofNearbyBuses extends ActionBarActivity {
    private ListView ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_nearby_buses);
        ConnectXML();
        Load_list();
    }
    private  void ConnectXML()
    {
        ls=(ListView)findViewById(R.id.lst_nearby_bus);
    }
    private void Load_list()
    {
        List<String> list=new ArrayList<String>();
        list.add("BUS 1");
        list.add("BUS 2");
        list.add("BUS 3");
        list.add("BUS 4");
        list.add("BUS 5");
        list.add("BUS 6");
        list.add("Ayus na!!");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        ls.setAdapter(adapter);

    }
}
