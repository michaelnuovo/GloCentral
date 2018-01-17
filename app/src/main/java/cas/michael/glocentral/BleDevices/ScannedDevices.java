package cas.michael.glocentral.BleDevices;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;

import cas.michael.glocentral.MainActivity.ScannedDeviceAdapterInterface;

/**
 * Wrapper singleton class for scanned LE devices.
 */
public class ScannedDevices {

    private static final String TAG = "ScannedDevices";

    ArrayList<ScannedDeviceAdapterInterface> mAdapters = new ArrayList<>();
    private static ScannedDevices singleton;
    private Context mCtx;
    private ArrayList<ScannedDevice> mScannedDevices = new ArrayList<>();

    private ScannedDevices(Context ctx){
        mCtx = ctx;
    }

    public static ScannedDevices getInstanceOf(Context ctx){
        if(singleton != null) return singleton;
        singleton = new ScannedDevices(ctx);
        return singleton;
    }

    public void addDevice(BluetoothDevice device, int rssi){
        Log.i(TAG,"addDevice()");


        //Log.d(TAG,"addDevice()");

        // If device is in list, just return, otherwise add a new device
        if(isDeviceInList(device)) return;

        Log.i(TAG,"added device : "
                + String.valueOf(device.getName() + "("+device.getAddress()+")"));

        // Add new device
        ScannedDevice newDevice = new ScannedDevice(device, rssi);
        mScannedDevices.add(newDevice);

        notifyAdapters();
    }

    public void removeDevice(){

        notifyAdapters();
    }

    public ArrayList<ScannedDevice> getList(){
        return mScannedDevices;
    }

    private boolean isDeviceInList(BluetoothDevice device){
        String address = device.getAddress();
        for(ScannedDevice scannedDevice : mScannedDevices){
            if(scannedDevice.mDevice.getAddress().equals(address)) return true;
        }
        return false;
    }

    // begin adapter handling

    public void addAdapter(ScannedDeviceAdapterInterface adapter){
        mAdapters.add(adapter);
    }

    private void notifyAdapters(){
        for(ScannedDeviceAdapterInterface adapter : mAdapters){
            adapter.resetData(mScannedDevices);
        }
    }

    // end adapter handling
}
