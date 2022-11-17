package com.example.rightcleaner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.helper.SessionManagement;

public class SimpleUserHomePage extends AppCompatActivity {
    RightCleanerDataBase rightCleanerDataBase;
    UserDAO userDAO;
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManagement=new SessionManagement(this);
        rightCleanerDataBase= RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
        userDAO=rightCleanerDataBase.userDAO();
        setContentView(R.layout.activity_simple_user_home_page);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        switch (item.getItemId()) {
            case R.id.homeP: {
                startActivity(new Intent(this,SimpleUserHomePage.class));
                break;
            }
            case R.id.profileP:{
                startActivity(new Intent(this,ProfilePage.class));
                break;
            }
            case  R.id.logOut:{
                sessionManagement.logoutUser();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!sessionManagement.isLoggedIn()) {
            sendToLoginPage();
        }
    }
    public void sendToLoginPage(){
        startActivity(new Intent(this,MainActivity.class));

    }



}