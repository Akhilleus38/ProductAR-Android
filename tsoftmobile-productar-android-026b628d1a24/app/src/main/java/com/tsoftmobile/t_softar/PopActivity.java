package com.tsoftmobile.t_softar;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PopActivity extends AppCompatActivity {

    Button btn_close,gonder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);


         btn_close = findViewById(R.id.button2);
         btn_close.setOnClickListener(v -> finish());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        int widht = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(widht*.9),(int)(height*.6));


        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

    }

}