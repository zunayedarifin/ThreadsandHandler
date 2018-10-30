package com.example.arifin.threadsandhandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            textView.setText("Good Work buddy");
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                working();
            }
        });
    }
    public void working(){

        Runnable r= new Runnable() {
            @Override
            public void run() {
                long futureTime=(System.currentTimeMillis()+10000);
                while (System.currentTimeMillis()<futureTime){
                    synchronized (this){
                        try{
                            wait(futureTime-System.currentTimeMillis());
                        }catch (Exception e){
                        }
                    }
                }
                handler.sendEmptyMessage(0);
            }
        };
        Thread thread= new Thread(r);
        thread.start();


    }
}
