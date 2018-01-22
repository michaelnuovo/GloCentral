package cas.michael.glocentral.LeScanner;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.util.Log;

import java.util.List;

import cas.michael.glocentral.BleDevices.ScannedDevices;
import cas.michael.glocentral.Constants;

public class LeScannerCallback extends ScanCallback {

    private static final String TAG = LeScannerCallback.class.getSimpleName();
    Context mCtx;


    public LeScannerCallback(Context ctx){
        Context mCtx = ctx;
        //mScannedDevices = scannedDevices;
    }

    /**
     * Callback when a BLE advertisement has been found.
     * @param callbackType
     * @param result
     */
    public void onScanResult(int callbackType, ScanResult result){

        //Log.i(TAG,"onScanResult()");

        BluetoothDevice device = result.getDevice();
        String deviceName = device.getName();
        boolean isInFilter = true; //Constants.DEVICE_FILTER.contains(deviceName);

        // Check if this device name is filtered or not
        if(isInFilter) {
            Log.i(TAG,"onScanResult()");
            ScannedDevices.getInstanceOf(mCtx).addDevice(device,result.getRssi());
        }
    }

    /**
     * Callback when batch results are delivered.
     * @param results
     */
    public void onBatchScanResults(List<ScanResult> results){
        Log.i(TAG,"onBatchScanResults()");
    }

    /**
     * Callback when scan could not be started.
     * @param errorCode
     */
    public void onScanFailed(int errorCode){

        Log.i(TAG,"onScanFailed(); error code = "+String.valueOf(errorCode));
    }
}
