package com.example.rightcleaner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rightcleaner.dao.MeetDAO;
import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.entity.Meet;
import com.example.rightcleaner.helper.SessionManagement;

public class ServiceMeet extends AppCompatActivity {
    RightCleanerDataBase rightCleanerDataBase;
    UserDAO userDAO;
    SessionManagement sessionManagement;
    EditText meetText,dateMeet;
    Button btnAddMeet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManagement=new SessionManagement(this);
        rightCleanerDataBase= RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
        userDAO=rightCleanerDataBase.userDAO();
        setContentView(R.layout.activity_meet);
        meetText=findViewById(R.id.meetText);
        dateMeet = findViewById(R.id.DateMeet);
        btnAddMeet=findViewById(R.id.btnAddMeet);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnAddMeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightCleanerDataBase rightCleanerDataBase=RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
                final MeetDAO meetDAO=rightCleanerDataBase.meetDAO();
                Meet meet = new Meet();
                meet.setEmailSimpleUser(sessionManagement.getUserDetails().get("email").toString());
                meet.setEmailServiceUser(sessionManagement.getProfile().get("email").toString());
                meet.setDescription(meetText.getText().toString());
                meet.setDate(dateMeet.getText().toString());
                if(validateInput(meet)){
                    meetDAO.addMeet(meet);
                    Toast.makeText(getApplicationContext(),"Add Review",Toast.LENGTH_SHORT).show();
                    Ine
                }else {
                    Toast.makeText(getApplicationContext(),"Make sure to fill all the fields",Toast.LENGTH_SHORT).show();
                }

            }
        });

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
                sessionManagement.cleanProfile();
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

    private Boolean validateInput(Meet meet){
        if(meet.getDescription().isEmpty()|| meet.getDate().isEmpty()){
            return  false;
        }
        return true;
    }

}
