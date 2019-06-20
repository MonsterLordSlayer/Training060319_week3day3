package com.example.week3day3;

import android.os.AsyncTask;
import android.util.Log;
import java.util.Arrays;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Array;

public class Async extends AsyncTask<String, String, String> {
    AsyncCallback asyncCallback;
    int[] array;
    public Async(AsyncCallback asyncCallback, int[] array) {
        this.asyncCallback = asyncCallback;
        this.array=array;
    }
    int num=0;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        Arrays.sort(array);
        return strings[strings.length - 1];
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Log.d("TAG", "onProgressUpdate: " + values[0]);


    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //EventBus.getDefault().post(new MessagingEvent(s));
        asyncCallback.returnString(s);
        returnarray();

    }
    public int[] returnarray(){
        for (int i=0;i<1000;i++){
            Log.d("TAG", "onPostExecute: "+array[i]);
        }
        return array;
    }

    interface AsyncCallback{
        void returnString(String string);
    }


}

