package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_dolist.Dao.QLAccount;
import com.example.to_dolist.Model.Account;

public class signInScr extends AppCompatActivity {

    TextView txtToSignIn;
    EditText txtSignInName, txtSignInEmail, txtSignInPass, txtSignInCfPass;
    Button btnRegis;
    QLAccount qlAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_scr);

        txtToSignIn = findViewById(R.id.txtToSignIn);
        txtSignInName = findViewById(R.id.txtSignInName);
        txtSignInEmail = findViewById(R.id.txtSignInEmail);
        txtSignInPass = findViewById(R.id.txtSignInPass);
        txtSignInCfPass = findViewById(R.id.txtSignInCfPass);
        btnRegis = findViewById(R.id.btnRegis);
        qlAccount = new QLAccount(signInScr.this);

        txtToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signInScr.this, logInScr.class);
                startActivity(intent);
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSignIn();
            }
        });

    }

    public void checkSignIn(){
//        Khai báo các biến get Dữ liệu vào
        String SiName = txtSignInName.getText().toString();
        String SiEmail = txtSignInEmail.getText().toString();
        String SiPass = txtSignInPass.getText().toString();
        String SiCfPass = txtSignInCfPass.getText().toString();
        boolean checkSignIn = true;

//        Check Username
        if(SiName.length() == 0){
            Toast.makeText(this, "Enter your UserName.", Toast.LENGTH_SHORT).show();
            checkSignIn = false;
        }
//        Check Email
        if(SiEmail.length() == 0){
            Toast.makeText(this, "Enter your Email.", Toast.LENGTH_SHORT).show();
            checkSignIn = false;
        }

        if(!(SiEmail.contains("@") && (SiEmail.contains(".")))){
            Toast.makeText(this, "Email is not formatted correctly.", Toast.LENGTH_SHORT).show();
            checkSignIn = false;
        }
//        Check Pass
        boolean checkPass = true;
        if(SiPass.length() == 0){
            Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();
            checkSignIn = false;
            checkPass = false;
        }
//        Check CfPass
        if(checkPass){
            if(SiCfPass.length() == 0){
                Toast.makeText(this, "Confirm your Password", Toast.LENGTH_SHORT).show();
                checkSignIn = false;

            }
//          Check Confirm Pass giống Pass
            else {
                if (!SiCfPass.equals(SiPass)){
                    Toast.makeText(this, "Confirm Password is Incorrect", Toast.LENGTH_SHORT).show();
                    checkSignIn = false;
                }
            }
        }
        else {
            Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();
        }

        if(checkSignIn){
            Account account = new Account(SiName, SiEmail, SiPass);
            if(qlAccount.themAccount(account) >= 0){
                Intent intent = new Intent(signInScr.this, logInScr.class);
                startActivity(intent);
            }   else {
                Toast.makeText(this, "Account is Exist", Toast.LENGTH_SHORT).show();
            }
            
        }
    };
}