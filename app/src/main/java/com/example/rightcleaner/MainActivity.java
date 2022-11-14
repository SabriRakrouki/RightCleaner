package com.example.rightcleaner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rightcleaner.database.RightCleanerDataBase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        RightCleanerDataBase rightCleanerDataBase= RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
        rightCleanerDataBase.isOpen();
     
       


    }



}