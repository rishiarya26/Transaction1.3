package com.example.rishiarya.transaction;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    JSONArray array;
    JSONObject result;
    ListView list;

    String MOT;
    String AMOUNT;
    String STATUS;
    String TRANS_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listview);

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                force();
                pullToRefresh.setRefreshing(false);
            }
        });

        force();
    }

    public void force() {

        String myUrl = "http://192.168.43.131:8080/user/hello";
        getrequest get = new getrequest();

        try {
            result = get.execute(myUrl).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //changee jsonobj into string
        String Result = result.get("1").toString();

        JSONParser par = new JSONParser();

        try
        {
            array = (JSONArray) par.parse(Result);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //initiating and setting adapter
        final customAdapter customAdapter = new customAdapter();
        list.setAdapter(customAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                JSONObject jobj = (JSONObject) array.get(position);
                String MOT = jobj.get("mot").toString();
                String Amount = jobj.get("amount").toString();
                String ID = jobj.get("id").toString();
                String Status = jobj.get("name").toString();

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("name", MOT);
                intent.putExtra("amount", Amount);
                intent.putExtra("status", Status);
                intent.putExtra("id", ID);

                startActivity(intent);
            }
        });
    }


    //user-defined adapter - customAdapter
    class customAdapter extends BaseAdapter {

        @Override // loop repetition count
        public int getCount() {

            return array.size();  //10
        }


        @Override
        public Object getItem(int position) {

            return 0;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }            //


        @Override   //setting view for 1 field of listview and iterating it
        public View getView(int i, View view, ViewGroup parent) {

            ///inflating customlayout
            view = getLayoutInflater().inflate(R.layout.customlayout, null);
            TextView date = (TextView) view.findViewById(R.id.date);
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView amount = (TextView) view.findViewById(R.id.amount);
            TextView status = (TextView) view.findViewById(R.id.status);
            TextView trans = (TextView) view.findViewById(R.id.trans);
            TextView monthss = (TextView) view.findViewById(R.id.dateup);


            String datepresent = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
            String months = new SimpleDateFormat("MMM", Locale.ENGLISH).format(new Date());

            JSONObject item = (JSONObject) array.get(i);

            AMOUNT = item.get("amount").toString();
            MOT = item.get("mot").toString();
            STATUS = item.get("name").toString();
            TRANS_ID = item.get("id").toString();

            name.setText(MOT);
            status.setText(STATUS);

            if (STATUS.equals("Success")) {

                status.setTextColor(Color.parseColor("#d532cfca"));
            } else {
                status.setTextColor(Color.parseColor("#BEFF000D"));
            }
            amount.setText(AMOUNT);
            trans.setText(TRANS_ID);
            date.setText(datepresent);
            monthss.setText(months);

            return view;


        }


    }


}


