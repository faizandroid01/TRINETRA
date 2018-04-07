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

import HelperClass.AdminProjectWisePackageListViewItems;

/**
 * Created by faiz on 5/4/18.
 */

public class SwipeAdminProjectWisePackageListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<AdminProjectWisePackageListViewItems> adminProjectWisePackageListViewItemsList;
    private String[] bgColors;


    public SwipeAdminProjectWisePackageListAdapter(Activity activity, List<AdminProjectWisePackageListViewItems> adminProjectWisePackageListViewItems) {
        this.activity = activity;
        this.adminProjectWisePackageListViewItemsList = adminProjectWisePackageListViewItems;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.listview_bg);

    }


    @Override
    public int getCount() {
        return adminProjectWisePackageListViewItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return adminProjectWisePackageListViewItemsList.get(position);
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
            convertView = inflater.inflate(R.layout.custom_row_admin_project_wise_package_listview, null);

        TextView packageName = (TextView) convertView.findViewById(R.id.custom_row_admin_package_name);
        TextView packageLocation = (TextView) convertView.findViewById(R.id.custom_row_admin_package_location);

        packageName.setText(String.valueOf(adminProjectWisePackageListViewItemsList.get(position).admin_package_name));
        packageLocation.setText(adminProjectWisePackageListViewItemsList.get(position).admin_package_loaction);

        String color = bgColors[position % bgColors.length];
        packageName.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }
}
