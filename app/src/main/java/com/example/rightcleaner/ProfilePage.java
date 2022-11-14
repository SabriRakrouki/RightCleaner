package com.example.rightcleaner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.entity.User;

public class ProfilePage extends AppCompatActivity {


    TextView fname,email,birthdate,phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        RightCleanerDataBase rightCleanerDataBase= RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
        UserDAO userDAO=rightCleanerDataBase.userDAO();
        User user = (User) getIntent().getSerializableExtra("user");
        fname=findViewById(R.id.fname);
        email=findViewById(R.id.emailT);
        birthdate=findViewById(R.id.birthDate);
        phone=findViewById(R.id.phone);
        if(user!=null){
            fname.setText(user.getFamilyName() +" "+user.getName());
            email.setText(user.getEmail());
            birthdate.setText("test");
            phone.setText(user.getPhoneNumber());

        }else{
            fname.setText("NNan");
            email.setText("NNan");
            birthdate.setText("NNan");
            phone.setText("NNan");
        }





    }
}