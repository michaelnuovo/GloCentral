package cas.michael.glocentral.MainActivity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cas.michael.glocentral.BleDevices.ScannedDevice;
import cas.michael.glocentral.R;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>
        implements ScannedDeviceAdapterInterface {

    private static final String TAG = MainAdapter.class.getSimpleName();

    private ArrayList<ScannedDevice> mScannedDevices;

    @Override
    public void resetData(ArrayList<ScannedDevice> mData){
        mScannedDevices = mData;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_device_name) TextView tv_device_name;

        // each data item is just a string in this case
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MainAdapter(ArrayList<ScannedDevice> myDataset) {
        mScannedDevices = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_activity_recycler_view_list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.d(TAG,"onBindViewHolder()");

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mScannedDevices[position]);
        ScannedDevice scannedDevice = mScannedDevices.get(position);
        holder.tv_device_name.setText(scannedDevice.mName);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mScannedDevices.size();
    }
}
