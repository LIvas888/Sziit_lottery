package com.example.bm_pc.sziit_lottery;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnStart;
    private TextView tvName;
    private Handler handler;
    private boolean isRunning;
    private List<String> names;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.button);
        tvName = (TextView) findViewById(R.id.textView);
        names = getNames();
        btnStart.setOnClickListener((View.OnClickListener) this);
        handler = new Handler(new InnerCallback());
    }

    public List<String> getNames() {
        List<String> names = new ArrayList<String>();
        names.add("曾浩源");
        names.add("李伟丽");
        names.add("高思莎");
        names.add("吴朝鑫");
        names.add("阮高远");
        names.add("黄龙城");
        names.add("陈金川");

        return names;


    }

    @Override
    public void onClick(View view) {
        if(isRunning == true){
            isRunning = false;
            btnStart.setText("Start");
        }else{
            isRunning = true;
            new InnerThread().start();
            btnStart.setText("Stop");
        }
    }

    private class InnerCallback implements Handler.Callback {


        @Override
        public boolean handleMessage(Message message) {
            int index = message.arg1;
            String name = names.get(index);
            tvName.setText(name);
            return false;
        }
    }

    private class InnerThread extends Thread{
        Random random = new Random();
        int nameCount = names.size();
        int ranNumber;
        long sleep = 500;
        @Override
        public void run() {
            while(isRunning){
                ranNumber = random.nextInt(nameCount);
                Message.obtain(handler, 0, ranNumber, 0).sendToTarget();
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
