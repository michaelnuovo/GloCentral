package cas.michael.glocentral.ServerActivity;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cas.michael.glocentral.BleDevices.GattAttribute.Inheritance.GattAttribute;
import cas.michael.glocentral.BleDevices.ScannedDevice;
import cas.michael.glocentral.BleDevices.ScannedDevices;
import cas.michael.glocentral.Constants;
import cas.michael.glocentral.R;

public class ServerActivity extends Activity implements MVP.View {

    private static final String TAG = ServerActivity.class.getSimpleName();

    ScannedDevice mDevice;
    Presenter mPresenter;

    BluetoothProfileEventReceiver mBluetoothProfileEventReceiver;

    @BindView(R.id.progress_bar_layout) LinearLayout mProgressBarLayout;
    @BindView(R.id.attribute_layout) LinearLayout mAttributeLayout;
    @BindView(R.id.server_activity_recycler_view) RecyclerView mRecyclerView;

    AttributeAdapter mAdapter;
    List<GattAttribute> mAttributes = new LinkedList(); // init empty list

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        ButterKnife.bind(this);
        mPresenter = new Presenter(this);

        String deviceAddress = getIntent().getStringExtra(Constants.KEY_DEVICE_ADDRESS);
        mDevice = ScannedDevices.getInstanceOf(this).getDeviceByAddress(deviceAddress);
        registerGattEventReceiver();
        mDevice.connect(); // Connection must be attempted after receiver is registered

        LinearLayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(lm);
        mAdapter = new AttributeAdapter(mAttributes, mPresenter);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void registerGattEventReceiver(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_BLUETOOTH_PROFILE_EVENT);
        filter.addAction(Constants.ACTION_BLUETOOTH_GATT_EVENT);
        mBluetoothProfileEventReceiver = new BluetoothProfileEventReceiver();
        registerReceiver(mBluetoothProfileEventReceiver, filter);
    }

    private class BluetoothProfileEventReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i(TAG,"onReceive()");

            String action = intent.getAction();

            switch (action){
                case Constants.ACTION_BLUETOOTH_PROFILE_EVENT:
                    handleProfileEvent(intent);
                    break;
                case Constants.ACTION_BLUETOOTH_GATT_EVENT:
                    handleGattEvent(intent);
                    break;
            }
        }
    }

    private void handleProfileEvent(Intent intent){
        int event = intent.getIntExtra(Constants.KEY_BLUETOOTH_PROFILE_EVENT,0);
        switch (event) {
            case BluetoothProfile.STATE_CONNECTED :
                Log.i(TAG,"BluetoothProfile.STATE_CONNECTED");
                mPresenter.gattEvent(event);
                break;
            case BluetoothProfile.STATE_DISCONNECTED :
                Log.i(TAG,"BluetoothProfile.STATE_DISCONNECTED");
                mPresenter.gattEvent(event);
                break;
        }
    }

    private void handleGattEvent(Intent intent){
        int status = intent.getIntExtra(Constants.KEY_BLUETOOTH_PROFILE_EVENT,0);
        switch (status) {
            case BluetoothGatt.GATT_SUCCESS :
                Log.i(TAG,"BluetoothGatt.GATT_SUCCESS");

                mAttributes = mDevice.getAttributes();
                mAdapter.resetData(mAttributes);
                Log.i(TAG,"Attributes.size() = "+String.valueOf(mAttributes.size()));
                break;
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mDevice.disconnect();
        unregisterReceiver(mBluetoothProfileEventReceiver);
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG,"onBackPressed");
        super.onBackPressed();
        mDevice.disconnect();
    }

    @Override
    public void toggleLayoutVisibility(int eventId) {
        switch (eventId) {
            case BluetoothProfile.STATE_CONNECTED :
                if(mProgressBarLayout.getVisibility() == View.VISIBLE){
                    mProgressBarLayout.setVisibility(View.INVISIBLE);
                    mAttributeLayout.setVisibility(View.VISIBLE);
                }
                break;
            case BluetoothProfile.STATE_DISCONNECTED :
                if(mProgressBarLayout.getVisibility() != View.VISIBLE){
                    mProgressBarLayout.setVisibility(View.VISIBLE);
                    mAttributeLayout.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }
}
