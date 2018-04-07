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

import HelperClass.AdminProjectListViewItems;

/**
 * Created by faiz on 5/4/18.
 */

public class SwipeAdminProjectListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<AdminProjectListViewItems> adminProjectList;
    private String[] bgColors;

    public SwipeAdminProjectListAdapter(Activity activity, List<AdminProjectListViewItems> adminProjectList) {
        this.activity = activity;
        this.adminProjectList = adminProjectList;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.listview_bg);

    }


    @Override
    public int getCount() {
        return adminProjectList.size();
    }

    @Override
    public Object getItem(int position) {
        return adminProjectList.get(position);
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
            convertView = inflater.inflate(R.layout.custom_row_admin_project_listview, null);

        TextView projectName = (TextView) convertView.findViewById(R.id.custom_row_admin_project_name);
        TextView projectLocation = (TextView) convertView.findViewById(R.id.custom_row_admin_project_location);

        projectName.setText(String.valueOf(adminProjectList.get(position).admin_project_name));
        projectLocation.setText(adminProjectList.get(position).admin_project_loaction);

        String color = bgColors[position % bgColors.length];
        projectName.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }
}
