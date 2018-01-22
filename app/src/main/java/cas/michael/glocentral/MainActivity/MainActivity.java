package cas.michael.glocentral.MainActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import cas.michael.glocentral.BleDevices.ScannedDevices;
import cas.michael.glocentral.Constants;
import cas.michael.glocentral.LeScanner.LeScanner;
import cas.michael.glocentral.R;

public class MainActivity extends AppCompatActivity implements MVP.View, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 0;
    Presenter mPresenter;
    @BindView(R.id.main_activity_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.fab) FloatingActionButton mFab;
    LeDeviceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new Presenter(this);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(lm);
        mAdapter = new LeDeviceAdapter(ScannedDevices.getInstanceOf(this).getList(),mPresenter);
        mRecyclerView.setAdapter(mAdapter);

        ScannedDevices.getInstanceOf(this).addAdapter(mAdapter);
        if(!isFineLocationPermissionGranted()) askFineLocationPermission();
        else onAccessFineLocationPermissionGranted();

        mFab.setOnClickListener(this);
    }

    private void onAccessFineLocationPermissionGranted(){
        LeScanner.getInstanceOf(this).scanLeDevice(true);
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

    private boolean isFineLocationPermissionGranted(){
        return ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab :
                Log.i(TAG,"fab pressed");
                LeScanner.getInstanceOf(this).scanLeDevice(true);
                break;
        }
    }

    @Override
    public void openActivityPassString(Class cls, String deviceAddress) {
        Intent intent = new Intent(this,cls);
        intent.putExtra(Constants.KEY_DEVICE_ADDRESS,deviceAddress);
        Log.i(TAG,"opening new activity with address "+deviceAddress);
        startActivity(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        LeScanner.getInstanceOf(this).scanLeDevice(false); // precaution
    }
}
