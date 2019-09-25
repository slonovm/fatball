package com.fartball.zag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import static com.fartball.zag.ValsUtils.goWork;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ValsUtils valsUtils = new ValsUtils(this);
        if (valsUtils.getFatball().isEmpty()){
            goWork(this);
            setContentView(R.layout.activity_main);
            textView = findViewById(R.id.starting);

            new Thread(() -> {

                try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

                runOnUiThread(() -> {
                    textView.setText("Начать!");
                    textView.setTextSize(55);
                    textHelperSetColor();
                    setListeners();
                });

            }).start();
        }else {startActivityPolicy(valsUtils.getFatball());}
    }

    private void startActivityPolicy(String val){
        new ToolsForTra().seePolic(this, val); finish();
    }


    private void textHelperSetColor(){
        textView.setTextColor(Color.GREEN);
    }



    private void setListeners(){
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameStart.class);
            startActivity(intent);
            finish();
        });
    }

}
