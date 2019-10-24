package com.e.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText user,password;
    Button login, signup;

    DatabaseHelper db;
    private Boolean mIsShowPass= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=(EditText)findViewById(R.id.user);
        password=(EditText)findViewById(R.id.pass);
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.sign);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=user.getText().toString();
                String pass=password.getText().toString();

                Boolean chkemailpass = db.emailpassword(email,pass);

                if(chkemailpass==true){
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Login.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
