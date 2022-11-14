package com.example.rightcleaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.entity.User;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        loginBtn = findViewById(R.id.loginBtn);
        RightCleanerDataBase rightCleanerDataBase = RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
        rightCleanerDataBase.isOpen();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                final UserDAO  userDAO=rightCleanerDataBase.userDAO();
                User user = userDAO.Login(name,pwd);
                if (user != null) {
                    Toast.makeText(getApplicationContext(),"Welcome to Right Cleaner",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



