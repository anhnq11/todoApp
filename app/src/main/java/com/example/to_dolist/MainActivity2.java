package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_dolist.Dao.QLUser;
import com.example.to_dolist.Model.User;
import com.example.to_dolist.MyAdapter.MyAdapter;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    TextView txtLogOut, txtTittle;
    ListView listTask;
    ImageView btnAddTask;
    QLUser qlUser;
    ArrayList<User> listUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtLogOut = findViewById(R.id.txtLogOut);
        txtTittle = findViewById(R.id.userName);
        listTask = findViewById(R.id.listTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        qlUser = new QLUser(MainActivity2.this);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");
        txtTittle.setText("Welcome " + userName);

        capNhatListTask(userName);

        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, logInScr.class);
                startActivity(intent);
            }
        });

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddTask(userName);
            }
        });
    }

    public void dialogAddTask(String userName){
        Dialog dialog = new Dialog(MainActivity2.this);
        dialog.setContentView(R.layout.dialog_add_task);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.9);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(width, height);

        EditText txtAddTask = dialog.findViewById(R.id.txtAddTask);
        Button btnDialogCancel = dialog.findViewById(R.id.btnDialogCancel);
        Button btnDialogAddTask = dialog.findViewById(R.id.btnDialogAddTask);

//        Them task moi vao danh sach
        btnDialogAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = txtAddTask.getText().toString();
                if(task.length() == 0){
                    Toast.makeText(MainActivity2.this, "Input your task", Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User(userName, task, false);
                    if(qlUser.themTask(user) >= 0){
                        capNhatListTask(userName);
                    }
                    else {
                        Toast.makeText(MainActivity2.this, "Failll", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            }
        });

        btnDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

//    Load lai danh sach
    public void capNhatListTask(String userName){
        listUser = qlUser.getAllTask(userName);
        MyAdapter myAdapter = new MyAdapter(listUser, MainActivity2.this);
        listTask.setAdapter(myAdapter);
    }

    public void xoaTask(String taskName, String userName){
        int i = qlUser.xoaTask(taskName);
    }
}