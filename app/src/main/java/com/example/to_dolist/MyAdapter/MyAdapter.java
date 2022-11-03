package com.example.to_dolist.MyAdapter;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_dolist.MainActivity2;
import com.example.to_dolist.Model.User;
import com.example.to_dolist.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<User> listUser;
    MainActivity2 mainActivity2;

    public MyAdapter(ArrayList<User> listUser,  MainActivity2 mainActivity2){
        this.listUser = listUser;
        this.mainActivity2 = mainActivity2;
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (mainActivity2).getLayoutInflater();
        convertView = inflater.inflate(R.layout.task_item, null);
        TextView  taskName = convertView.findViewById(R.id.taskItemTittle);
        CheckBox taskStatus = convertView.findViewById(R.id.taskItemChkBox);

        taskName.setText(listUser.get(index).getTaskUser());
        taskStatus.setChecked(listUser.get(index).isTaskStatus());

        taskStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                User userDelete = listUser.get(index);
                String taskName = userDelete.getTaskUser();
                String userName = userDelete.getNameUser();
                mainActivity2.xoaTask(taskName, userName);
            }
        });

        return convertView;
    }

}
