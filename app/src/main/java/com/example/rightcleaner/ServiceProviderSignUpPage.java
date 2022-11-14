package com.example.rightcleaner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.dao.UserServiceProviderDAO;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.entity.User;
import com.example.rightcleaner.entity.UserServiceProvider;

public class ServiceProviderSignUpPage extends AppCompatActivity {

    EditText username,email,phoneNumber,pass;
    Spinner service,price;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_sign_up_page);
        username=findViewById(R.id.usernameService);
        email=findViewById(R.id.emailService);
        phoneNumber=findViewById(R.id.phoneService);
        pass=findViewById(R.id.passwordService);
        String[] arraySpinner = new String[] {
                "House Cleaning", "Gardener", "Electrician"
        };
        String[] arraySpinnerPrice = new String[] {
                "10 - 20", "20 - 30", "30 - 40","Other"
        };
        service=findViewById(R.id.service);
        price=findViewById(R.id.price);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        service.setAdapter(adapter);
        ArrayAdapter<String> adapterPrice = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerPrice);
        adapterPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        price.setAdapter(adapterPrice);
        signup=findViewById(R.id.btnSignupService);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightCleanerDataBase rightCleanerDataBase=RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
                final UserDAO  userDAO=rightCleanerDataBase.userDAO();
                final UserServiceProviderDAO  userServiceProviderDAO=rightCleanerDataBase.userServiceProviderDAO();
                UserServiceProvider user = new UserServiceProvider();

                user.setEmail(email.getText().toString());
                user.setPassword(pass.getText().toString());
                user.setPhoneNumber(phoneNumber.getText().toString());
                user.setService(service.getSelectedItem().toString());
                user.setPrice(price.getSelectedItem().toString());
                user.setRole("serviceUser");
                    if(validateInput(user)){
                        userDAO.register(user);
                        userServiceProviderDAO.register(user);
                        Toast.makeText(getApplicationContext(),"You haved registed",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Make sure to fill all the fields",Toast.LENGTH_SHORT).show();
                    }

            }
        });

    }


    private Boolean validateInput(UserServiceProvider userServiceProvider){
        if(userServiceProvider.getEmail().isEmpty()|| userServiceProvider.getPassword().isEmpty()||userServiceProvider.getPhoneNumber().isEmpty()){
            return  false;
        }
        return true;
    }

}