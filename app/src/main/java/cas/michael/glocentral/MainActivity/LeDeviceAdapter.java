package cas.michael.glocentral.MainActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cas.michael.glocentral.BleDevices.ScannedDevice;
import cas.michael.glocentral.R;
import mehdi.sakout.fancybuttons.FancyButton;

public class LeDeviceAdapter extends RecyclerView.Adapter<LeDeviceAdapter.ViewHolder> {

    private static final String TAG = LeDeviceAdapter.class.getSimpleName();

    private List<ScannedDevice> mScannedDevices;
    private Presenter mPresenter;

    public void resetData(List<ScannedDevice> mData){
        mScannedDevices = mData;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Presenter mPresenter;
        ScannedDevice mScannedDevice;

        @BindView(R.id.tv_device_name) TextView mTvDeviceName;
        @BindView(R.id.tv_mac_address) TextView mTvDeviceAddress;
        @BindView(R.id.btn_connect) FancyButton mBtnConnect;

        // each data item is just a string in this case
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mBtnConnect.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            Log.i(TAG,"onClick");
            switch (view.getId()){
                case R.id.btn_connect :
                    mPresenter.onConnectButtonPressed(mScannedDevice.mAddress);
                    break;
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public LeDeviceAdapter(ArrayList<ScannedDevice> myDataset, Presenter presenter) {
        mScannedDevices = myDataset;
        mPresenter = presenter;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LeDeviceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_activity_recycler_view_list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        vh.mPresenter = mPresenter;

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.i(TAG,"onBindViewHolder()");

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mScannedDevices[position]);
        ScannedDevice scannedDevice = mScannedDevices.get(position);
        holder.mScannedDevice = scannedDevice;
        holder.mTvDeviceName.setText(scannedDevice.mName);
        holder.mTvDeviceAddress.setText(scannedDevice.mAddress);
        //holder.mAddress = scannedDevice.mAddress;
        Log.i(TAG,"binding address "+String.valueOf(scannedDevice.mAddress));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mScannedDevices.size();
    }
}
