package com.example.rightcleaner;

import static android.content.Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP;
import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rightcleaner.dao.ReviewDAO;
import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.dao.UserServiceProviderDAO;
import com.example.rightcleaner.database.RightCleanerDataBase;
import com.example.rightcleaner.entity.Review;
import com.example.rightcleaner.entity.User;
import com.example.rightcleaner.entity.UserServiceProvider;
import com.example.rightcleaner.helper.ReviewStatus;
import com.example.rightcleaner.helper.Role;
import com.example.rightcleaner.helper.ServiceCategory;
import com.example.rightcleaner.helper.SessionManagement;

public class ServiceReview extends AppCompatActivity {

    EditText reviewText;
    Spinner spinnerReview;
    Button addReview;
    RightCleanerDataBase rightCleanerDataBase;
    ReviewDAO reviewDAO;
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        sessionManagement=new SessionManagement(this);
        rightCleanerDataBase= RightCleanerDataBase.getRightCleanerDataBase(getApplicationContext());
        reviewDAO=rightCleanerDataBase.reviewDAO();
        reviewText=findViewById(R.id.ReviewText);
        String[] arraySpinnerReview = new String[] {
                ReviewStatus.GOOD.toString(), ReviewStatus.AVERAGE.toString(), ReviewStatus.BAD.toString()
        };
        spinnerReview=findViewById(R.id.spinnerReview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerReview);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerReview.setAdapter(adapter);
        addReview=findViewById(R.id.btnAddReview);
        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Review review = new Review();

                review.setReview(reviewText.getText().toString());
                review.setStatus(spinnerReview.getSelectedItem().toString());
                review.setEmailSimpleUser(sessionManagement.getUserDetails().get("email").toString());
                review.setEmailServiceUser(sessionManagement.getProfile().get("profile").toString());
                if(validateInput(review)){
                    reviewDAO.addReview(review);
                    Toast.makeText(getApplicationContext(),"Add Review",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Make sure to fill all the fields",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private Boolean validateInput(Review review){
        if(review.getReview().isEmpty()|| review.getStatus().isEmpty()){
            return  false;
        }
        return true;
    }

}