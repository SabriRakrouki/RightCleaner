package com.example.rightcleaner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.entity.User;
import com.example.rightcleaner.helper.SessionManagement;

public class ProfilePage extends AppCompatActivity {


    TextView fname,email,birthdate,phone;
    RightCleanerDataBase rightCleanerDataBase;
    UserDAO userDAO;
    SessionManagement sessionManagement;
    Button addrev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManagement=new SessionManagement(getApplicationContext());
        rightCleanerDataBase= RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
        userDAO=rightCleanerDataBase.userDAO();
        setContentView(R.layout.activity_profile_page);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RightCleanerDataBase rightCleanerDataBase= RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
        UserDAO userDAO=rightCleanerDataBase.userDAO();
        User user = (User) getIntent().getSerializableExtra("user");
        fillProfile();
        if(user!=null){
            fname.setText(user.getUsername());
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
                startActivity(new Intent(this,ServiceChoice.class));
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
        fillProfile();
    }
    public void sendToLoginPage(){
        startActivity(new Intent(this,MainActivity.class));

    }
    public void fillProfile(){
        fname=findViewById(R.id.fname);
        addrev=findViewById(R.id.addRev);
        email=findViewById(R.id.emailT);
        birthdate=findViewById(R.id.birthDate);
        phone=findViewById(R.id.phone);
        User user=userDAO.getUserId(Integer.parseInt(sessionManagement.getUserDetails().get("id").toString()));
        fname.setText(user.getUsername());
        email.setText(user.getEmail());
        phone.setText(user.getPhoneNumber());
        if(sessionManagement.getProfile().get("profile").toString().isEmpty()==false){
            if(user.getEmail().equals(sessionManagement.getProfile().get("profile").toString())){
                addrev.setVisibility(View.GONE);
            }
        }else {
            addrev.setVisibility(View.GONE);
        }




    }
}