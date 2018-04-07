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

import HelperClass.SupervisorProjectWisePackageListViewItems;

/**
 * Created by faiz on 5/4/18.
 */

public class SwipeSupervisorProjectWisePackageListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SupervisorProjectWisePackageListViewItems> supervisorProjectWisePackageListViewItemsList;
    private String[] bgColors;

    public SwipeSupervisorProjectWisePackageListAdapter(Activity activity, List<SupervisorProjectWisePackageListViewItems> supervisorProjectWisePackageListViewItemsList) {
        this.activity = activity;
        this.supervisorProjectWisePackageListViewItemsList = supervisorProjectWisePackageListViewItemsList;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.listview_bg);

    }


    @Override
    public int getCount() {
        return supervisorProjectWisePackageListViewItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return supervisorProjectWisePackageListViewItemsList.get(position);
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
            convertView = inflater.inflate(R.layout.custom_row_supervisor_project_wise_package_listview, null);

        TextView supervisorPackageName = (TextView) convertView.findViewById(R.id.custom_row_admin_project_name);
        TextView supervisorProjectLocation = (TextView) convertView.findViewById(R.id.custom_row_admin_project_location);

        supervisorPackageName.setText(String.valueOf(supervisorProjectWisePackageListViewItemsList.get(position).supervisor_package_name));
        supervisorProjectLocation.setText(supervisorProjectWisePackageListViewItemsList.get(position).supervisor_package_location);

        String color = bgColors[position % bgColors.length];
        supervisorPackageName.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }
}
