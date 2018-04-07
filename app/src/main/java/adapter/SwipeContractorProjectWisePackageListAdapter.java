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

import HelperClass.ContractorProjectWisePackageListViewItems;

/**
 * Created by faiz on 5/4/18.
 */

public class SwipeContractorProjectWisePackageListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ContractorProjectWisePackageListViewItems> contractorProjectWisePackageListViewItemsList;
    private String[] bgColors;

    public SwipeContractorProjectWisePackageListAdapter(Activity activity, List<ContractorProjectWisePackageListViewItems> contractorProjectWisePackageListViewItemsList) {
        this.activity = activity;
        this.contractorProjectWisePackageListViewItemsList = contractorProjectWisePackageListViewItemsList;
    }


    @Override
    public int getCount() {
        return contractorProjectWisePackageListViewItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contractorProjectWisePackageListViewItemsList.get(position);
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
            convertView = inflater.inflate(R.layout.custom_row_contractor_project_wise_package_listview, null);

        TextView contractorPackageName = (TextView) convertView.findViewById(R.id.custom_row_admin_project_name);
        TextView contractorPackageLocation = (TextView) convertView.findViewById(R.id.custom_row_admin_project_location);

        contractorPackageName.setText(String.valueOf(contractorProjectWisePackageListViewItemsList.get(position).contractor_package_name));
        contractorPackageLocation.setText(contractorProjectWisePackageListViewItemsList.get(position).contractor_package_location);

        String color = bgColors[position % bgColors.length];
        contractorPackageName.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }
}
