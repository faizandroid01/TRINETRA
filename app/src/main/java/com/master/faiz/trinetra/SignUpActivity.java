package com.master.faiz.trinetra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        spinner = (Spinner) findViewById(R.id.user_signup_type_spinner);
        String spinner_items []= {"ADMIN","CONTRACTOR","SUPERVISOR"};

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sign Up");
        toolbar.setSubtitle("Mecon Ltd.");

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,spinner_items);
        spinner.setAdapter(aa);

    }

    public void userSignUp(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}
