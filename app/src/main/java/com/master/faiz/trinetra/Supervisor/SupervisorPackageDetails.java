package com.master.faiz.trinetra.Supervisor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.master.faiz.trinetra.R;

public class SupervisorPackageDetails extends AppCompatActivity {


    Toolbar toolbar;
    ListView sup_package_listView;
    String package_name;
    String project_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_package_details);

        sup_package_listView = (ListView) findViewById(R.id.activity_supervisor_login_package_listview);
        final String items[] = {"pk1","pk2","pk3"};

        Bundle b=getIntent().getExtras();
        if(b!=null){
            project_name =  b.getString("project_name");
        }

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle(project_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.appbar_text_color));

        ArrayAdapter<String> aa = new ArrayAdapter<String>(SupervisorPackageDetails.this,android.R.layout.simple_dropdown_item_1line,items);
        sup_package_listView.setAdapter(aa);

        sup_package_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                package_name = items[position];
                Intent i = new Intent(SupervisorPackageDetails.this,SupervisorShiftAssignment.class);
                i.putExtra("package_name",package_name);
                startActivity(i);

            }
        });


    }
}
