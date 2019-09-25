package com.fartball.zag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import static com.fartball.zag.ValsUtils.init;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ValsUtils valsUtils = new ValsUtils(this);
        if (valsUtils.getData().isEmpty()){
            init(this);
            setContentView(R.layout.activity_main);
            textView = findViewById(R.id.starting);

            new Thread(() -> {

                try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

                runOnUiThread(() -> {
                    textView.setText("Начать!");
                    textView.setTextSize(45);
                    textView.setTextColor(Color.GREEN);
                    textView.setOnClickListener(view -> {
                        Intent intent = new Intent(MainActivity.this, GameStart.class);
                        startActivity(intent);
                        finish();
                    });
                });

            }).start();
        }else { new Utils().showPolicy(this, valsUtils.getData()); finish(); }
    }
}
