package com.example.matt.trasy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.os.StrictMode;

import static java.lang.Thread.sleep;

public class MainActivity extends ActionBarActivity   implements View.OnClickListener, AsyncResponse  {
   private ImageButton imgbtn_tire_and_lights
            ,imgbtn_engine
            ,imgbtn_accident
            ,imgbtn_otherincident
            ,imgbtn_nearby_buses
            ,imgbtn_police_hotline;
   private TextView  txt_bus_name
                 ,txt_plate_no
                 ,txt_location;
    private final int ALERT_DIALOG=1;
    private String Action="";
    private  String mesage="";
    JSONParser JSONConnect = new JSONParser();
    private static final String TAG_Success = "success";
    private static final String TAG_noti_msg ="noti_msg";

    private double longitude_=0;
    private double latitude_=0;
    GPSTracker gps;

    Timer timer;
    MyTimerTask myTimerTask;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImplementsConnectionAndEvents();
        txt_bus_name.setText("Bus name : Busname Sample");
        txt_plate_no.setText("Plate no. : XX00 Sample");
        txt_location.setText("Location : BULACAN Sample");
        StrictMode.ThreadPolicy strict = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(strict);

        /**
        gps=new GPSTracker(MainActivity.this);
        for(;;)
        {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("bus_id","1"));
            params.add(new BasicNameValuePair("longitude", gps.getLongitude() + ""));
            params.add(new BasicNameValuePair("latitude", gps.getLatitude() + ""));
            insert(params, "insert_activebus.php");/////PHP insert statement!!!
            longitude_=gps.getLongitude();
            latitude_=gps.getLatitude();
            txt_bus_name.setText(longitude_+" "+latitude_);
            try{
                sleep(10000);
            }
            catch (Exception xx){

            }
        }
**/




    }
    protected void onStart(Bundle savedInstanceState)
    {
       // timer.schedule(myTimerTask, 1000, 5000);
        //Log.w("Start here","start");
    }
    private void ImplementsConnectionAndEvents()
    {
        ////Connection
        ///IMAGE BUTTON
        imgbtn_tire_and_lights=(ImageButton)findViewById(R.id.imgbtn_tire_and_lights);
        imgbtn_engine=(ImageButton)findViewById(R.id.imgbtn_engine);
        imgbtn_accident=(ImageButton)findViewById(R.id.imgbtn_accident);
        imgbtn_otherincident=(ImageButton)findViewById(R.id.imgbtn_otherincident);
        imgbtn_nearby_buses=(ImageButton)findViewById(R.id.imgbtn_nearby_buses);
        imgbtn_police_hotline=(ImageButton)findViewById(R.id.imgbtn_police_hotline);
        ///TXTVIEW
        txt_bus_name=(TextView)findViewById(R.id.txt_bus_name);
        txt_plate_no=(TextView)findViewById(R.id.txt_plate_no);
        txt_location=(TextView)findViewById(R.id.txt_location);
        /////////////events
        imgbtn_tire_and_lights.setOnClickListener(this);
        imgbtn_engine.setOnClickListener(this);
        imgbtn_accident.setOnClickListener(this);
        imgbtn_otherincident.setOnClickListener(this);
        imgbtn_nearby_buses.setOnClickListener(this);
        imgbtn_police_hotline.setOnClickListener(this);
    }

    @Override
    public void onClick(View click) {
        if(click==imgbtn_tire_and_lights){
            gps=new GPSTracker(MainActivity.this);
            Action="tire and light";
      //      MainActivity.this.showDialog(ALERT_DIALOG);
            changeColor("imgbtn_tire_and_lights");

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("message","Sample Message"));
            params.add(new BasicNameValuePair("user_id","123"));
            params.add(new BasicNameValuePair("date","1/2/2016"));
            params.add(new BasicNameValuePair("type", "sample_alfie"));
            params.add(new BasicNameValuePair("longitude", ""+gps.getLongitude()));
            params.add(new BasicNameValuePair("latitude", ""+gps.getLatitude()));
            params.add(new BasicNameValuePair("plate_no", "234"));
            params.add(new BasicNameValuePair("bus_id", "333"));
            txt_bus_name.setText("");
            txt_bus_name.setText(gps.getLongitude()+"");
            insert(params, "insert_message.php");
        }
        if(click==imgbtn_engine){
            Action="engine";
           // changeColor("imgbtn_engine");
         //   MainActivity.this.showDialog(ALERT_DIALOG);
            timer.schedule(myTimerTask, 1000, 5000);

        }
        if(click==imgbtn_accident){
            Action="accident";
            changeColor("imgbtn_accident");
            MainActivity.this.showDialog(ALERT_DIALOG);

        }
        if(click==imgbtn_otherincident){
            Action="other incident";
            changeColor("imgbtn_otherincident");
            MainActivity.this.showDialog(ALERT_DIALOG);
        }
        if(click==imgbtn_nearby_buses){
            changeColor("imgbtn_nearby_buses");
            startActivity(new Intent(MainActivity.this, ListofPoliceHotline.class));
        }
        if(click==imgbtn_police_hotline){
            changeColor("imgbtn_police_hotline");
            startActivity(new Intent(MainActivity.this, ListofNearbyBuses.class));
        }
    }
    protected Dialog onCreateDialog(int id) {
        Dialog dialog=null;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        switch (id)
        {
            case ALERT_DIALOG:
                builder.setMessage("Are you sure you want to send Message")
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AfterClickEventMessage();

                            }
                        }).setNegativeButton(
                        "No",new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"Message Canceled",Toast.LENGTH_SHORT).show();

                            }
                        }

                )
                        .setCancelable(false);
                dialog=builder.create();
                break;
            default:
                break;

        }
        return  dialog;
    }
    private  void AfterClickEventMessage()
    {
        Toast.makeText(MainActivity.this,"Message sent for "+Action.toUpperCase(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void processFinish(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
    private  void changeColor(String imgbtn_name)
    {
            switch (imgbtn_name)
            {
                case "imgbtn_tire_and_lights":
                    setButton_color(imgbtn_police_hotline, false);
                    setButton_color(imgbtn_nearby_buses,false);
                    setButton_color(imgbtn_otherincident,false);
                    setButton_color(imgbtn_accident,false);
                    setButton_color(imgbtn_engine,false);
                    setButton_color(imgbtn_tire_and_lights,true);
                    break;
                case "imgbtn_engine":
                    setButton_color(imgbtn_police_hotline, false);
                    setButton_color(imgbtn_nearby_buses,false);
                    setButton_color(imgbtn_otherincident,false);
                    setButton_color(imgbtn_accident,false);
                    setButton_color(imgbtn_engine,true);
                    setButton_color(imgbtn_tire_and_lights,false);
                    break;
                case "imgbtn_accident":
                    setButton_color(imgbtn_police_hotline, false);
                    setButton_color(imgbtn_nearby_buses,false);
                    setButton_color(imgbtn_otherincident,false);
                    setButton_color(imgbtn_accident,true);
                    setButton_color(imgbtn_engine,false);
                    setButton_color(imgbtn_tire_and_lights,false);
                    break;
                case "imgbtn_otherincident":
                    setButton_color(imgbtn_police_hotline, false);
                    setButton_color(imgbtn_nearby_buses,false);
                    setButton_color(imgbtn_otherincident,true);
                    setButton_color(imgbtn_accident,false);
                    setButton_color(imgbtn_engine,false);
                    setButton_color(imgbtn_tire_and_lights,false);
                    break;
                case "imgbtn_nearby_buses":
                    setButton_color(imgbtn_police_hotline, false);
                    setButton_color(imgbtn_nearby_buses,true);
                    setButton_color(imgbtn_otherincident,false);
                    setButton_color(imgbtn_accident,false);
                    setButton_color(imgbtn_engine,false);
                    setButton_color(imgbtn_tire_and_lights,false);
                    break;
                case "imgbtn_police_hotline":

                    setButton_color(imgbtn_police_hotline, true);
                    setButton_color(imgbtn_nearby_buses,false);
                    setButton_color(imgbtn_otherincident,false);
                    setButton_color(imgbtn_accident,false);
                    setButton_color(imgbtn_engine,false);
                    setButton_color(imgbtn_tire_and_lights,false);

                    break;
            }

    }
    private  void setButton_color(ImageButton b,boolean bol)
    {
        b.setBackgroundColor(Color.rgb(207, 33, 73));
        if(bol)
        {
            b.setBackgroundColor(Color.rgb(246,207,0));
        }
        //LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        // Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        // double longitude = location.getLongitude();
        // double latitude = location.getLatitude();
    }
    private  void insert( List<NameValuePair> params,String phpfile)
    {

        JSONObject json = JSONConnect.makeHttpRequest(JSONConnect.Mainurl() + phpfile,"POST",params);
        Toast.makeText(getApplication(), "here", Toast.LENGTH_LONG);
        try {
            int Success =  json.getInt(TAG_Success);
            String Message =  json.getString(TAG_noti_msg);
                if(Success==1){
                    Toast.makeText(getApplication(), "Record Inserted", Toast.LENGTH_LONG);
                }
                if(Success==0){
                    Toast.makeText(getApplication(), Message, Toast.LENGTH_LONG);
                }
            }
        catch (Exception e) {

            Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG);
            }
    }


    private class MyTimerTask extends TimerTask {
        public void run() {

            runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("bus_id","1"));
                    params.add(new BasicNameValuePair("longitude", "2"));
                    params.add(new BasicNameValuePair("latitude", "2"));
                    params.add(new BasicNameValuePair("user_id","2"));
                    params.add(new BasicNameValuePair("datetime","sample date FROM ANDROID"));
                    insert(params, "insert_activebus.php");/////PHP insert statement!!!
                }});
        }
    }

}
