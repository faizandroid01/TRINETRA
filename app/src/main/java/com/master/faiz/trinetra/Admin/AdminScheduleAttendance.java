package com.master.faiz.trinetra.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.master.faiz.trinetra.R;

public class AdminScheduleAttendance extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_schedule_attendance);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("Admin Schedule Attendance");

    }

    public void adminScheduleAttendance(View view) {
    }
}
