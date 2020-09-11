package com.app.gadsleaderboard;

import android.os.Bundle;

import com.app.gadsleaderboard.api.service.UserClient;
import com.developer.kalert.KAlertDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitActivity extends AppCompatActivity {

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://docs.google.com/forms/u/0/d/e/")
            .addConverterFactory(GsonConverterFactory.create());
    public static  Retrofit retrofit = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                KAlertDialog successDialog = new KAlertDialog(SubmitActivity.this, KAlertDialog.SUCCESS_TYPE);
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
                KAlertDialog successDialog = new KAlertDialog(SubmitActivity.this, KAlertDialog.ERROR_TYPE);
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
