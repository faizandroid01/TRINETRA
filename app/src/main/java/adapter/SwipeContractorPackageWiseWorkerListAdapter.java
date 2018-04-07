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

import HelperClass.ContractorPackageWiseWorkerListViewItems;

/**
 * Created by faiz on 5/4/18.
 */

public class SwipeContractorPackageWiseWorkerListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ContractorPackageWiseWorkerListViewItems> contractorPackageWiseWorkerListViewItemsList;
    private String[] bgColors;

    public SwipeContractorPackageWiseWorkerListAdapter(Activity activity, List<ContractorPackageWiseWorkerListViewItems> contractorPackageWiseWorkerListViewItemsList) {
        this.activity = activity;
        this.contractorPackageWiseWorkerListViewItemsList = contractorPackageWiseWorkerListViewItemsList;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.listview_bg);

    }


    @Override
    public int getCount() {
        return contractorPackageWiseWorkerListViewItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contractorPackageWiseWorkerListViewItemsList.get(position);
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
            convertView = inflater.inflate(R.layout.custom_row_contractor_package_wise_worker_listview, null);

        TextView packageWorkerName = (TextView) convertView.findViewById(R.id.custom_row_admin_project_name);
        TextView packageWorkerType = (TextView) convertView.findViewById(R.id.custom_row_admin_project_location);

        packageWorkerName.setText(String.valueOf(contractorPackageWiseWorkerListViewItemsList.get(position).package_wise_worker_name));
        packageWorkerType.setText(contractorPackageWiseWorkerListViewItemsList.get(position).package_wise_worker_type);

        String color = bgColors[position % bgColors.length];
        packageWorkerName.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }
}
