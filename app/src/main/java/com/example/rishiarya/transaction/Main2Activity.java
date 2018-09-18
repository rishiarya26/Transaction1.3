package com.example.rishiarya.transaction;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    String name;
    String amount;
    String id;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.detials);

        //getting intent values from other activity
        name = getIntent().getStringExtra("name"); //forString
        amount = getIntent().getStringExtra("amount"); //forInt
        id = getIntent().getStringExtra("id");
        status = getIntent().getStringExtra("status");

        TextView t1 = (TextView) findViewById(R.id.paiddetail);
        TextView t2 = (TextView) findViewById(R.id.amount);
        TextView t3 = (TextView) findViewById(R.id.transid);
        TextView t4 = (TextView) findViewById(R.id.date);
        TextView t5 = (TextView) findViewById(R.id.status);
        TextView t6 = (TextView) findViewById(R.id.paiddetailnum);
        TextView t7 = (TextView) findViewById(R.id.transid2);
        TextView t8 = (TextView) findViewById(R.id.date2);
        TextView t9 = (TextView) findViewById(R.id.status2);

        //for current date
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());

        t1.setText(name);
        t2.setText("Amount");
        t3.setText("Trans.ID");
        t4.setText("Date");
        t5.setText("status");
        t6.setText(String.valueOf(amount));
        t7.setText(String.valueOf(id));
        t8.setText(date_n);
        t9.setText(status);
    }
}
