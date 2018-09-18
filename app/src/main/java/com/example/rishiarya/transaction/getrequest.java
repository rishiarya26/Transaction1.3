package com.example.rishiarya.transaction;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.ls.LSInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class getrequest extends AsyncTask<String, Void, JSONObject> {


    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    String output;

    @Override
    protected JSONObject doInBackground(String... params) {
        String stringUrl = params[0];

        JSONObject json = new JSONObject();

        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);

            //Create a connection
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();

            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to our url
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            while ((output = br.readLine()) != null) {

                JSONParser parser = new JSONParser();
                json = (JSONObject) parser.parse(output);
            }

        } catch (IOException e) {
            e.printStackTrace();


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return json;
    }
}

