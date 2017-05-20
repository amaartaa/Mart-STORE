package com.example.uti.mart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuMM extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mm);
    }

    public void onClickPesan2(View v){
        Intent iPesan2 = new Intent(MenuMM.this, Main2Activity.class);
        startActivity(iPesan2);                                                                         //Ke tab order
    }
}
