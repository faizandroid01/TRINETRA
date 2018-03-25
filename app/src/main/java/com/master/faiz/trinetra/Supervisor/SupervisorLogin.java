package com.master.faiz.trinetra.Supervisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.master.faiz.trinetra.R;

public class SupervisorLogin extends AppCompatActivity {

    Toolbar toolbar;
    ListView sup_project_listView;
    String project_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_login);

        sup_project_listView = (ListView) findViewById(R.id.supervisor_project_listview);
        final String []items = {"P1","P2","P3","P4"};

        toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        toolbar.setTitle("PROJECTS");

        ArrayAdapter<String> aa= new ArrayAdapter<String>(this , android.R.layout.simple_dropdown_item_1line , items);
        sup_project_listView.setAdapter(aa);

        sup_project_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                project_name = items[position];
                Intent intent = new Intent(SupervisorLogin.this,SupervisorPackageDetails.class );
                intent.putExtra("project_name",project_name);
                startActivity(intent);

            }
        });

    }
}
