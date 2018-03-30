package com.master.faiz.trinetra.Admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.master.faiz.trinetra.R;

import java.util.HashMap;
import java.util.Map;

import Utils.DataWrapper;
import Utils.VolleySingleton;

public class AdminScheduleAttendance extends AppCompatActivity {

    Toolbar toolbar;
    DatePicker dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_schedule_attendance);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("Schedule Attendance");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));


        dp = (DatePicker) findViewById(R.id.activity_admin_schedule_attendance_date_picker);

    }

    public void adminScheduleAttendance(View view) {

        final String date = "" + dp.getDayOfMonth() + "/" + dp.getMonth() + "/" + dp.getYear();
        //           final int date = Integer.parseInt(date_string);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, DataWrapper.BASE_URL_TEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("", date);


                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);





    }
}
