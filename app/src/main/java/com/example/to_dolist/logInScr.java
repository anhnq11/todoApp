package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_dolist.Dao.QLAccount;
import com.example.to_dolist.Model.Account;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class logInScr extends AppCompatActivity {


    EditText txtLogInName, txtLogInPass;
    CheckBox chkRememberMe;
    Button btnLogIn;
    TextView txtToSignUp;
    ImageView btnLoginWFb, btnLoginWGg;
    QLAccount qlAccount;
    List<Object> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_scr);

        txtLogInName = findViewById(R.id.txtLogInName);
        txtLogInPass = findViewById(R.id.txtLogInPass);
        chkRememberMe = findViewById(R.id.chkRememberMe);
        btnLogIn = findViewById(R.id.btnLogin);
        txtToSignUp = findViewById(R.id.txtToSignUp);
        btnLoginWFb = findViewById(R.id.btnLoginWFb);
        btnLoginWGg = findViewById(R.id.btnLoginWGg);
        qlAccount = new QLAccount(logInScr.this);

//      Đọc dữ liệu trong SR được gọi từ khi chương trình được khởi động
        list = readPassWord();
        if(list.size() > 0){
            txtLogInName.setText(list.get(0).toString());
            txtLogInPass.setText(list.get(1).toString());
            chkRememberMe.setChecked((boolean) list.get(2));
        }

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogIn();
            }
        });

        btnLoginWFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(logInScr.this, "Login With FB", Toast.LENGTH_SHORT).show();
            }
        });

        btnLoginWGg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(logInScr.this, "Login with GG", Toast.LENGTH_SHORT).show();
            }
        });

        txtToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(logInScr.this, signInScr.class);
                startActivity(intent);
            }
        });
    }

    public void checkLogIn(){
        String LoName = txtLogInName.getText().toString();
        String LoPass = txtLogInPass.getText().toString();
        boolean status = chkRememberMe.isChecked();
        boolean checkLogIn = true;
//        Check độ dài của UserName/Email
        if(LoName.length() == 0){
            Toast.makeText(this, "Enter your UserName.", Toast.LENGTH_SHORT).show();
            checkLogIn = false;
        }

        if(LoPass.length() == 0){
            Toast.makeText(this, "Enter your Password.", Toast.LENGTH_SHORT).show();
            checkLogIn = false;
        }

        if(checkLogIn){
            Account account = new Account(LoName, LoPass);
            if(qlAccount.checkAccount(account) == 1){ // Tìm kiếm Account trong CSDL
                savePass(LoName, LoPass, status); // SavePass để kiểm tra status của checkBox
                Intent intent = new Intent(logInScr.this, MainActivity2.class);
                intent.putExtra("name", LoName);
                startActivity(intent);
            }   else {
//                Dialog
                Dialog dialog = new Dialog(logInScr.this);
                dialog.setContentView(R.layout.dialog_account_not_exist);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.9);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.7);
                dialog.getWindow().setLayout(width, height);

                Button btnCloseDialog = dialog.findViewById(R.id.btnClose);

                btnCloseDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        }
    }

    void savePass(String userName, String passWord, boolean status){
        SharedPreferences save = getSharedPreferences("FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        if (status == false){
//            Nếu status checkBox == false -> Không lưu dữ liệu
            editor.clear();
        }
        else {
//            Nếu status checkBox == true -> Lưu dữ liệu
            editor.putString("userName", userName);
            editor.putString("passWord", passWord);
            editor.putBoolean("status", status);
        }
        editor.commit();
    }

    List<Object> readPassWord(){
        List<Object> list = new ArrayList<>();
        SharedPreferences save = getSharedPreferences("FILE", MODE_PRIVATE);
        list.add(save.getString("userName", ""));
        list.add(save.getString("passWord", ""));
        list.add(save.getBoolean("status", false));
        return list;
    }
}