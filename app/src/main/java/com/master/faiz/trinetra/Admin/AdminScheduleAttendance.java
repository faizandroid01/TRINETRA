package com.master.faiz.trinetra.Admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;

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
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_schedule_attendance);

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("Schedule Attendance");
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));
        linearLayout = (LinearLayout) findViewById(R.id.activity_admin_schedule_attendance_linear_layout);


        dp = (DatePicker) findViewById(R.id.activity_admin_schedule_attendance_date_picker);

    }

    public void adminScheduleAttendance(View view) {

        final String date = "" + dp.getDayOfMonth() + "/" + dp.getMonth() + "/" + dp.getYear();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, DataWrapper.BASE_URL_TEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


               /*     if(status==false)
                    {
                       Snackbar.make(linearLayout , "Attendance isn't scheduled .. " , Snackbar.LENGTH_SHORT);

                    }else
                        {
                            Snackbar.make(linearLayout , "Attendance is scheduled ..  " , Snackbar.LENGTH_SHORT);
                             startActivity(new Intent(AdminAddProject.this,AdminProject.class));
                        }
*/

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

                params.put("module", DataWrapper.QMODULE_USER);
                params.put("query", DataWrapper.Q_CREATE_ACCOUNT);
                params.put("query_type", DataWrapper.QTYPE_I);

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}
