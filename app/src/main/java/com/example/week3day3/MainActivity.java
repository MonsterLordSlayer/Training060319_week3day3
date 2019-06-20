package com.example.week3day3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> fibs=new ArrayList<>();
    TextView tvFib;
    TextView tvPi;
    int count=3;
    double  pi=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvFib=findViewById(R.id.tvFib);
        tvPi=findViewById(R.id.tvPi);
        fibs.add(1);
        fibs.add(1);

        for(Double i=0.0;i<12;i+=1.0){
            Thread thread = new Thread(runnable(i));
            thread.run();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        tvPi.setText("pi "+pi);



//        Thread thread2 = new Thread(runnable2());

        LooperDemoThread looperDemoThread;


        looperDemoThread = new LooperDemoThread(new Handler(Looper.getMainLooper()){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //get info back out of message received
                Bundle bundle = msg.getData();
                int result=bundle.getInt("key");
                fibs.add(result);


                LooperDemoThread looperDemoThread;
                if (fibs.size()<20){
                    looperDemoThread = new LooperDemoThread(this);
                    looperDemoThread.start();
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("key1", fibs.get(fibs.size()-1));
                    bundle2.putInt("key2", fibs.get(fibs.size()-2));

                    Message message=new Message();
                    message.setData(bundle2);
                    looperDemoThread.workerThreadHandler.sendMessage(message);
                }
                else {
                    tvFib.setText("FIB of "+count+": "+bundle.getInt("key"));
                }
                count++;






            }
        });
        looperDemoThread.start();


        Bundle bundle = new Bundle();
        bundle.putInt("key1", fibs.get(fibs.size()-1));
        bundle.putInt("key2", fibs.get(fibs.size()-2));

        Message message=new Message();
        message.setData(bundle);
        looperDemoThread.workerThreadHandler.sendMessage(message);





















    }
    public Runnable runnable(double k) {
        final double finalk=k;
        Runnable returnRunnable = new Runnable() {
            @Override
            public void run() {
                pi+=Math.pow((1.0/16.0),finalk)*(4.0/(8.0*finalk+1.0)-2.0/(8.0*finalk+4.0)-1.0/(8*finalk+5.0)-1.0/(8*finalk+6));

            }
        };
        return returnRunnable;
    }
}
