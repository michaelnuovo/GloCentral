package cas.michael.glocentral.LeScanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import java.util.logging.LogRecord;

import cas.michael.glocentral.BleDevices.ScannedDevices;

import static android.content.Context.BLUETOOTH_SERVICE;

/**
 * references:
 * https://developer.android.com/reference/android/bluetooth/le/BluetoothLeScanner.html
 * https://developer.android.com/guide/topics/connectivity/bluetooth-le.html
 */

public class LeScanner {

    static private final String TAG = LeScanner.class.getSimpleName();

    private static LeScanner singleton;

    private boolean mScanning = false;
    private static final int SCAN_PERIOD = 1000;

    private BluetoothLeScanner mBluetoothScanner;
    private Handler mHandler = new Handler();
    private Context mCtx;

    private LeScanner(Context ctx){
        mCtx = ctx;
        BluetoothManager bluetoothManager = (BluetoothManager) ctx.getSystemService(BLUETOOTH_SERVICE);
        BluetoothAdapter mBluetoothAdapter = bluetoothManager.getAdapter();
        mBluetoothScanner = mBluetoothAdapter.getBluetoothLeScanner();
    }

    public static LeScanner getInstanceOf(Context ctx){
        if(singleton != null) return singleton;
        singleton = new LeScanner(ctx);
        return singleton;
    }

    public void scanLeDevice(final boolean enable) {

        Log.i(TAG,"scanLeDevice()");

        if (enable) {

            // Stop any current scanning process as a precaution
            stopScan();

            Log.i(TAG,"scanning...");

            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    stopScan();
                }
            }, SCAN_PERIOD);

            mScanning = true;
            ScannedDevices.getInstanceOf(mCtx).clear();
            startScan();

        } else {

            mScanning = false;
            stopScan();
        }
    }

    private void startScan(){
        mBluetoothScanner.startScan(new LeScannerCallback(mCtx));
    }

    private void stopScan(){
        Log.i(TAG,"stopScan");
        mBluetoothScanner.stopScan(new LeScannerCallback(mCtx));
    }
}
