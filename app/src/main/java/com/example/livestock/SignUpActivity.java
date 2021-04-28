package com.example.livestock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUpActivity extends AppCompatActivity {

    EditText textInputEditTextfullname, textInputEditTextemail, textInputEditTextnumber, textInputEditTextpassword;
    Button buttonsignin;
    TextView textViewlogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textInputEditTextfullname = findViewById(R.id.etFull_name);
        textInputEditTextemail = findViewById(R.id.add_mail);
        textInputEditTextemail = findViewById(R.id.add_number);
        textInputEditTextemail = findViewById(R.id.add_password);
        progressBar = findViewById(R.id.progress);

        buttonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname, email, mobilenumber, password;
                fullname = String.valueOf(textInputEditTextfullname.getText());
                email = String.valueOf(textInputEditTextemail.getText());
                mobilenumber = String.valueOf(textInputEditTextnumber.getText());
                password = String.valueOf(textInputEditTextpassword.getText());

                if (!fullname.equals("") && !email.equals("") && !mobilenumber.equals("") && !password.equals("")) {

                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @SuppressLint("ShowToast")
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "Fullname";
                            field[1] = "email";
                            field[2] = "Mobilenumber";
                            field[3] = "Password";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = "fullname";
                            data[1] = "email";
                            data[2] = "number";
                            data[3] = "password";
                            PutData putData = new PutData("http://demoxyzcenter.xyz/project/main/user", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("SignUp Successfull")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
                                        Intent intent = new Intent(getApplicationContext(), SignIn.class);
                                        startActivity(intent);
                                        finish();


                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
                                    }

                                }

                            }


                        }
                        //End Write and Read data with URL


                    });
                } else {
                    Toast.makeText(getApplicationContext(), "All Fields Required", Toast.LENGTH_SHORT).show();

                }
            }


        });
    }}