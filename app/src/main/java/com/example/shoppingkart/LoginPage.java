package com.example.shoppingkart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button btn1,btn2;
    EditText email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        sharedPreferences= getSharedPreferences("Mypref",MODE_PRIVATE);
        btn1 = findViewById(R.id.lg);
        btn2 = findViewById(R.id.reg);
        email = findViewById(R.id.userid);
        pass = findViewById(R.id.password);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=email.getText().toString().trim();
                String pw=pass.getText().toString().trim();

                String Useremail=sharedPreferences.getString("email","");
                String Userpassword=sharedPreferences.getString("password","");
                if(em.equals(""))
                {
                    Toast.makeText(LoginPage.this,"Email cant be blank",Toast.LENGTH_LONG).show();
                }
                else if(pw.equals(""))
                {
                    Toast.makeText(LoginPage.this,"Password cant be blank",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(em.equals(Useremail) && pw.equals(Userpassword))
                    {

                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putInt("login",1);
                        editor.apply();

                        Toast.makeText(LoginPage.this,"Login successfull",Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(LoginPage.this, Nav.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginPage.this,"Login Unsuccessfull",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LoginPage.this,Register.class);
                startActivity(intent2);
            }
        });
    }
}
