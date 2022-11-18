package com.example.rightcleaner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.dao.UserServiceProviderDAO;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.entity.User;
import com.example.rightcleaner.entity.UserServiceProvider;
import com.example.rightcleaner.helper.Role;
import com.example.rightcleaner.helper.SessionManagement;
import com.example.rightcleaner.listServiceProviders.ServiceProviderAdapter;
import com.example.rightcleaner.listServiceProviders.ServiceReviewAdapter;

public class ProfilePage extends AppCompatActivity {


    TextView fname,email,phone,price;
    RightCleanerDataBase rightCleanerDataBase;
    RecyclerView listReview;
    UserDAO userDAO;
    UserServiceProviderDAO  userServiceProviderDAO;
    SessionManagement sessionManagement;
    Button addrev;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=this;
        sessionManagement=new SessionManagement(getApplicationContext());
        rightCleanerDataBase= RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
        userDAO=rightCleanerDataBase.userDAO();
        userServiceProviderDAO=rightCleanerDataBase.userServiceProviderDAO();
        listReview=findViewById(R.id.listReview);
        ServiceReviewAdapter adapter = new ServiceReviewAdapter(this);
        listReview.setAdapter(adapter);
        listReview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        setContentView(R.layout.activity_profile_page);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RightCleanerDataBase rightCleanerDataBase= RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
        UserDAO userDAO=rightCleanerDataBase.userDAO();
        User user = (User) getIntent().getSerializableExtra("user");
        fillProfile();
        price.setText("NNan");

        if(user!=null){
            fname.setText(user.getUsername());
            email.setText(user.getEmail());
            price.setText("test");
            phone.setText(user.getPhoneNumber());

        }else{
            fname.setText("NNan");
            email.setText("NNan");
            price.setText("NNan");
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


    @Override
    protected void onStart() {
        super.onStart();

        listReview=findViewById(R.id.listReview);
        ServiceReviewAdapter adapter = new ServiceReviewAdapter(this);
        listReview.setAdapter(adapter);
        listReview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
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
        phone=findViewById(R.id.phone);
        price=findViewById(R.id.birthDate);
        User user=userDAO.getUserId(Integer.parseInt(sessionManagement.getUserDetails().get("id").toString()));
        fname.setText(user.getUsername());
        price.setText("NaN");
        email.setText(user.getEmail());
        phone.setText(user.getPhoneNumber());
        Log.i("test profile",""+user.getEmail().equals(sessionManagement.getProfile().get("profile").toString()));
        if(sessionManagement.getProfile().get("profile").toString().isEmpty()){
            addrev.setVisibility(View.GONE);
        }else{
            UserServiceProvider userP= userServiceProviderDAO.getByEmail(sessionManagement.getProfile().get("profile").toString());
            fname.setText(userP.getUsername());
            price.setText(userP.getPrice());
            email.setText(userP.getEmail());
            phone.setText(userP.getPhoneNumber());
        }

        addrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=  new Intent(context,ServiceReview.class);
                startActivity(intent);
            }
        });




    }
}