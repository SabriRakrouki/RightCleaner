package com.example.rightcleaner;

import androidx.appcompat.app.AppCompatActivity;

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

public class SignUp extends AppCompatActivity {
        EditText username,email,phoneNumber,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username=findViewById(R.id.userName);
        email=findViewById(R.id.email);
        phoneNumber=findViewById(R.id.phoneN);
        pass=findViewById(R.id.pass);
      Button  signup=findViewById(R.id.btnSignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User nUser=new User();
                RightCleanerDataBase rightCleanerDataBase=RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());

                final UserDAO userDAO=rightCleanerDataBase.userDAO();
                    nUser.setEmail(email.getText().toString());
                    nUser.setPassword(pass.getText().toString());
                    nUser.setPhoneNumber(phoneNumber.getText().toString());
                    nUser.setRole(Role.SIMPLE_USER.toString());
                    if(validateInput(nUser)){

                       new Thread(new Runnable() {
                           @Override
                           public void run() {
                               userDAO.register(nUser);
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(getApplicationContext(),"You haved registed",Toast.LENGTH_SHORT).show();
                                   }
                               });

                           }
                       }).start();
                    }else {
                        Toast.makeText(getApplicationContext(),"Make sure to fill all the fields",Toast.LENGTH_SHORT).show();
                    }
                Log.i("data",userDAO.toString());

            }
        });

    }


    private Boolean validateInput(User user){
        if(user.getEmail().isEmpty()|| user.getPassword().isEmpty()||user.getPhoneNumber().isEmpty()){
            return  false;
        }
        return true;
    }

}