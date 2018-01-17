package cas.michael.glocentral.MainActivity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import cas.michael.glocentral.BleDevices.ScannedDevice;
import cas.michael.glocentral.BleDevices.ScannedDevices;
import cas.michael.glocentral.R;
import cas.michael.glocentral.LeScanner.LeScannerCallback;

public class MainActivity extends AppCompatActivity
         {

    private static final String TAG = MainActivity.class.getSimpleName();

    /* Permissions constant(s) */
    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 0;

    /* Collection of scanned devices */
    private ArrayList<ScannedDevice> mScannedDevices = new ArrayList<>();

    /* UI */
    @BindView(R.id.main_activity_recycler_view) RecyclerView mRecyclerView;
    MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v(TAG,"onCreate()");
        Log.i(TAG,"onCreate()");
        Log.d(TAG,"onCreate()");
        Log.e(TAG,"onCreate()");
        Log.wtf(TAG,"onCreate()");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        askFineLocationPermission();
        initAdapter();
        startLeScan();

    }

    private void initAdapter(){

        // use a linear layout manager
        LinearLayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(lm);

        // specify an adapter (see also next example)
        mAdapter = new MainAdapter(ScannedDevices.getInstanceOf(this).getList());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void startLeScan(){

        Log.d(TAG,"startLeScan()");

        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        BluetoothLeScanner bluetoothScanner = bluetoothAdapter.getBluetoothLeScanner();
        bluetoothScanner.startScan(new LeScannerCallback(this,mScannedDevices));
        ScannedDevices.getInstanceOf(this).addAdapter(mAdapter);
    }

    private void askFineLocationPermission(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_ACCESS_FINE_LOCATION);

                // MY_PERMISSIONS_ACCESS_FINE_LOCATION is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // related task you need to do.

                    onAccessFineLocationPermissionGranted();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    finish();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void onAccessFineLocationPermissionGranted(){
        Log.i(TAG,"onAccessFineLocationPermissionGranted()");
    }
}
