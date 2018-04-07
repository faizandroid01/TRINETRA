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

import HelperClass.ContractorWorkerListViewItems;

/**
 * Created by faiz on 5/4/18.
 */

public class SwipeContractorWorkerListAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    private List<ContractorWorkerListViewItems> contractorWorkerListViewItemsList;
    private String[] bgColors;

    public SwipeContractorWorkerListAdapter(Activity activity, List<ContractorWorkerListViewItems> contractorWorkerListViewItemsList) {
        this.activity = activity;
        this.contractorWorkerListViewItemsList = contractorWorkerListViewItemsList;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.listview_bg);
    }


    @Override
    public int getCount() {
        return contractorWorkerListViewItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contractorWorkerListViewItemsList.get(position);
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
            convertView = inflater.inflate(R.layout.custom_row_contractor_worker_listview, null);

        TextView workerName = (TextView) convertView.findViewById(R.id.custom_row_admin_project_name);
        TextView workerType = (TextView) convertView.findViewById(R.id.custom_row_admin_project_location);

        workerName.setText(String.valueOf(contractorWorkerListViewItemsList.get(position).worker_name));
        workerType.setText(contractorWorkerListViewItemsList.get(position).worker_type);

        String color = bgColors[position % bgColors.length];
        workerName.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }
}
