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

import HelperClass.AdminPackageWiseContractorListViewItems;

/**
 * Created by faiz on 5/4/18.
 */

public class SwipeAdminPackageWiseContractorListAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    private List<AdminPackageWiseContractorListViewItems> adminPackageWiseContractorListViewItemsList;
    private String[] bgColors;

    public SwipeAdminPackageWiseContractorListAdapter(Activity activity, List<AdminPackageWiseContractorListViewItems> adminPackageWiseContractorListViewItems) {
        this.activity = activity;
        this.adminPackageWiseContractorListViewItemsList = adminPackageWiseContractorListViewItems;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.listview_bg);

    }


    @Override
    public int getCount() {
        return adminPackageWiseContractorListViewItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return adminPackageWiseContractorListViewItemsList.get(position);
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
            convertView = inflater.inflate(R.layout.custom_row_admin_package_wise_contractor_listview, null);

        TextView packageWisecontractorName = (TextView) convertView.findViewById(R.id.custom_row_admin_project_name);

        packageWisecontractorName.setText(String.valueOf(adminPackageWiseContractorListViewItemsList.get(position).package_wise_contractor_name));

        String color = bgColors[position % bgColors.length];
        packageWisecontractorName.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }
}
