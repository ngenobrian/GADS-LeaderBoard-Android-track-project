package com.app.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gadsleaderboard.api.service.UserClient;
import com.developer.kalert.KAlertDialog;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Test extends AppCompatActivity {

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://docs.google.com/forms/u/0/d/e/")
            .addConverterFactory(GsonConverterFactory.create());
    public static  Retrofit retrofit = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        try {
            final EditText firstname = findViewById(R.id.et_firstname);
            final EditText lastname = findViewById(R.id.et_lastname);
            final EditText email = findViewById(R.id.et_email);
            final EditText link = findViewById(R.id.et_link);
            findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    executeSendFeedbackForm(
                            firstname.getText().toString(),
                            lastname.getText().toString(),
                            email.getText().toString(),
                            link.getText().toString()

                    );
                }
            });

        }
        catch (Exception e){
            e.getMessage();
        }



    }

    private void executeSendFeedbackForm(String firstname, String lastname, String email, String link) {
        UserClient userClient = retrofit.create(UserClient.class);
        Call<ResponseBody> call = userClient.sendUserFeedback(firstname, lastname, email, link);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Toast.makeText(Test.this, "sucess", Toast.LENGTH_LONG).show();
                KAlertDialog successDialog = new KAlertDialog(Test.this, KAlertDialog.SUCCESS_TYPE);
                        successDialog.setContentText("Submission Successful!")
//                        .setCustomImage(R.drawable.top_learner)
                        .show();
                TextView message = findViewById(R.id.txtMessae);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        successDialog.dismiss();
                    }
                }, 3000);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KAlertDialog successDialog = new KAlertDialog(Test.this, KAlertDialog.ERROR_TYPE);
                successDialog.setContentText("Submission Not Successful")
//                        .setCustomImage(R.drawable.top_learner)
                        .show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        successDialog.dismiss();
                    }
                }, 3000);

            }

        });

    }




}
