package com.example.rightcleaner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rightcleaner.dao.UserServiceProviderDAO;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.entity.UserServiceProvider;

public class ServiceProviderSignUpPage extends AppCompatActivity {

    EditText username,email,phoneNumber,pass,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_sign_up_page);
        username=findViewById(R.id.usernameService);
        email=findViewById(R.id.emailService);
        phoneNumber=findViewById(R.id.phoneService);
        pass=findViewById(R.id.passwordService);
        String[] arraySpinner = new String[] {
                "Gardener", "House Cleaner"
        };
        Spinner service = findViewById(R.id.service);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        service.setAdapter(adapter);
        Button signup=findViewById(R.id.btnSignupService);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserServiceProvider userServiceProvider=new UserServiceProvider();
                userServiceProvider.setEmail(email.getText().toString());
                userServiceProvider.setPassword(pass.getText().toString());
                userServiceProvider.setPhoneNumber(phoneNumber.getText().toString());
                userServiceProvider.setService(service.getSelectedItem().toString());
                userServiceProvider.setPrice(Float.parseFloat(price.getText().toString()));
                userServiceProvider.setRole("serviceUser");
                    if(validateInput(userServiceProvider)){
                        RightCleanerDataBase rightCleanerDataBase=RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
                       final UserServiceProviderDAO userServiceProviderDAO=rightCleanerDataBase.userServiceProviderDAO();
                       new Thread(new Runnable() {
                           @Override
                           public void run() {
                               userServiceProviderDAO.register(userServiceProvider);
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

            }
        });

    }


    private Boolean validateInput(UserServiceProvider userServiceProvider){
        if(userServiceProvider.getEmail().isEmpty()|| userServiceProvider.getPassword().isEmpty()||userServiceProvider.getPhoneNumber().isEmpty()||userServiceProvider.getService().isEmpty()||userServiceProvider.getPrice().isNaN()){
            return  false;
        }
        return true;
    }

}