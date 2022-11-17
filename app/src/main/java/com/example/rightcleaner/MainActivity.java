package com.example.rightcleaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.entity.User;
import com.example.rightcleaner.helper.Role;
import com.example.rightcleaner.helper.SessionManagement;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginBtn;
    RightCleanerDataBase rightCleanerDataBase;
    UserDAO  userDAO;
    SessionManagement sessionManagement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
         sessionManagement=new SessionManagement(MainActivity.this);
         rightCleanerDataBase= RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
          userDAO=rightCleanerDataBase.userDAO();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        loginBtn = findViewById(R.id.loginBtn);

        rightCleanerDataBase.isOpen();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }

    public void login(View view){
        String name = username.getText().toString();
        String pwd = password.getText().toString();
        Log.i("name",name);
        Log.i("pass",pwd);
        User user = userDAO.Login(name,pwd);
        if(user!=null){
            Log.i("name",user.getEmail());
            Log.i("pass",user.getPassword());
            sessionManagement.createLoginSession(user.getEmail(),user.getPassword(),user.getId());
            moveToHomePage(user);
        }else {
            Toast.makeText(getApplicationContext(),"Check your inputs",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(sessionManagement.isLoggedIn()){
            Log.i("id", sessionManagement.getUserDetails().get("id").getClass().toString());
            moveToHomePage(userDAO.getUserId(Integer.parseInt(sessionManagement.getUserDetails().get("id").toString())));
        }


    }
    public void moveToHomePage(User user){
        if(user.getRole().equals(Role.SIMPLE_USER.toString())){
            Intent intent = new Intent(MainActivity.this, SimpleUserHomePage.class);
            startActivity(intent);

        }else if(user.getRole().equals(Role.Service_Provider.toString())) {
            Intent intent = new Intent(MainActivity.this, ServiveProviderHomePage.class);
            startActivity(intent);
        }
    }


}



