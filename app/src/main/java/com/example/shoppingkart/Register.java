package com.example.shoppingkart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Button btn1,btn2;
    EditText name,mail,ph,pwd,pwdc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

       btn1 = findViewById(R.id.btn);
       btn2 = findViewById(R.id.btn1);
       name = findViewById(R.id.name);
       mail = findViewById(R.id.mail);
       ph = findViewById(R.id.ph);
       pwd = findViewById(R.id.pwd);
       pwdc = findViewById(R.id.pwd1);

       sharedPreferences= getSharedPreferences("Mypref",MODE_PRIVATE);
       btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String n = name.getText().toString().trim();
               String em = mail.getText().toString().trim();
               String p = ph.getText().toString().trim();
               String pw = pwd.getText().toString().trim();
               String pwc = pwdc.getText().toString().trim();

               SharedPreferences.Editor editor= sharedPreferences.edit();

               if(em.equals("") )
               {
                   Toast.makeText(Register.this,"Email cant be blank",Toast.LENGTH_LONG).show();
               }
               else if(pw.equals("") || pwc.equals("") )
               {
                   Toast.makeText(Register.this,"Password cant be blank",Toast.LENGTH_LONG).show();
               }
               else if(n.equals(""))
               {
                   Toast.makeText(Register.this,"Name Section cant be blank",Toast.LENGTH_LONG).show();
               }
               else if(p.equals(""))
               {
                   Toast.makeText(Register.this,"Phone Number cant be blank",Toast.LENGTH_LONG).show();
               }
               else
               {

                   if (pw.equals(pwc))
                   {
                       editor.putString("email",em);
                       editor.putString("password",pw);
                       editor.putString("phone",p);
                       editor.putString("name",n);
                       editor.apply();
                       Intent i = new Intent(Register.this,LoginPage.class);
                       Toast.makeText(Register.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                       startActivity(i);
                       finish();
                   }
                   else
                   {
                       Toast.makeText(Register.this,"Password Doesnt Match",Toast.LENGTH_LONG).show();
                   }
               }
           }
       });

       btn2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Register.this,LoginPage.class);
               startActivity(intent);
           }
       });
    }
}
