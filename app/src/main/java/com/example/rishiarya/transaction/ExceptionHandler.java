package com.example.rishiarya.transaction;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

public class ExceptionHandler implements
        java.lang.Thread.UncaughtExceptionHandler {
    private final Activity myContext;
   // private final String LINE_SEPARATOR = "\n";

    public ExceptionHandler(Activity context) {
        myContext = context;
    }

    public void uncaughtException(Thread thread, Throwable exception) {


        Intent intent = new Intent(myContext, MainActivity.class);
        Toast.makeText(myContext, "error Server not running",
               Toast.LENGTH_LONG).show();

   //  intent.putExtra("error", errorReport.toString());
        myContext.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

}
