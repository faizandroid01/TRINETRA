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

import HelperClass.ContractorProjectListViewItems;

/**
 * Created by faiz on 5/4/18.
 */

public class SwipeContractorProjectListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ContractorProjectListViewItems> contractorProjectListViewItemsList;
    private String[] bgColors;

    public SwipeContractorProjectListAdapter(Activity activity, List<ContractorProjectListViewItems> contractorProjectListViewItemsList) {
        this.activity = activity;
        this.contractorProjectListViewItemsList = contractorProjectListViewItemsList;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.listview_bg);

    }


    @Override
    public int getCount() {
        return contractorProjectListViewItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contractorProjectListViewItemsList.get(position);
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
            convertView = inflater.inflate(R.layout.custom_row_contractor_project_listview, null);

        TextView contractorProjectName = (TextView) convertView.findViewById(R.id.custom_row_admin_project_name);
        TextView contractorProjectLocation = (TextView) convertView.findViewById(R.id.custom_row_admin_project_location);

        contractorProjectName.setText(String.valueOf(contractorProjectListViewItemsList.get(position).contractor_project_name));
        contractorProjectLocation.setText(contractorProjectListViewItemsList.get(position).contractor_project_loaction);

        String color = bgColors[position % bgColors.length];
        contractorProjectName.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }
}
