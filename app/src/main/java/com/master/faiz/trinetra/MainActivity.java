package com.master.faiz.trinetra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.master.faiz.trinetra.Admin.AdminProject;
import com.master.faiz.trinetra.Contractor.ContractorLogin;
import com.master.faiz.trinetra.Supervisor.SupervisorLogin;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);


        userName = (EditText) findViewById(R.id.MainActivity_username);
    }

    public void userLogin(View v) {

        Toast.makeText(this, "Login Fun", Toast.LENGTH_SHORT).show();

        String a = userName.getText().toString();

        if(a.equalsIgnoreCase("a")) {
            //check the login and password (related to either admin/cont/supervisor) and transfer the control to user login screen.
            startActivity(new Intent(this, AdminProject.class));
        }else if(a.equalsIgnoreCase("s"))
        {
            startActivity(new Intent(this, SupervisorLogin.class));

        }
        else if(a.equalsIgnoreCase("c"))
        {
            startActivity(new Intent(this, ContractorLogin.class));

        }
    }

    public void userRegister(View v) {
        Toast.makeText(this, "Sign Up Fun", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this,SignUpActivity.class));


    }
}
