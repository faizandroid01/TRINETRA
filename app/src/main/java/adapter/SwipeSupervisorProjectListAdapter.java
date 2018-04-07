package adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.master.faiz.trinetra.R;

import java.util.List;

import HelperClass.SupervisorProjectListViewItems;

/**
 * Created by faiz on 5/4/18.
 */

public class SwipeSupervisorProjectListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SupervisorProjectListViewItems> supervisorProjectListViewItemsList;
    private String[] bgColors;

    public SwipeSupervisorProjectListAdapter(Activity activity, List<SupervisorProjectListViewItems> supervisorProjectListViewItemsList) {
        this.activity = activity;
        this.supervisorProjectListViewItemsList = supervisorProjectListViewItemsList;

        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.listview_bg);

    }


    @Override
    public int getCount() {
        return supervisorProjectListViewItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return supervisorProjectListViewItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_row_supervisor_project_listview, null);

        TextView supervisorProjectName = (TextView) convertView.findViewById(R.id.custom_row_admin_project_name);
        TextView supervisorProjectLocation = (TextView) convertView.findViewById(R.id.custom_row_admin_project_location);

        supervisorProjectName.setText(String.valueOf(supervisorProjectListViewItemsList.get(position).supervisor_project_name));
        supervisorProjectLocation.setText(supervisorProjectListViewItemsList.get(position).supervisor_project_location);

        String color = bgColors[position % bgColors.length];
        supervisorProjectName.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }
}
