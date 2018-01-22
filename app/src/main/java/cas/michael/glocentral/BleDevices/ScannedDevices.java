package cas.michael.glocentral.BleDevices;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;

import cas.michael.glocentral.MainActivity.LeDeviceAdapter;

/**
 * Wrapper singleton class for scanned LE devices.
 */
public class ScannedDevices {

    private static final String TAG = "ScannedDevices";

    ArrayList<LeDeviceAdapter> mAdapters = new ArrayList<>();
    private static ScannedDevices singleton;
    private ArrayList<ScannedDevice> mScannedDevices = new ArrayList<>();
    private Context mCtx;

    private ScannedDevices(Context ctx){mCtx = ctx;}

    public static ScannedDevices getInstanceOf(Context ctx){
        if(singleton != null) return singleton;
        singleton = new ScannedDevices(ctx);
        return singleton;
    }

    public void addDevice(BluetoothDevice device, int rssi){

        // If device is in list, just return, otherwise add a new device
        if(isDeviceInList(device)) return;

        Log.i(TAG,"added device : "
                + String.valueOf(device.getName() + "("+device.getAddress()+")"));

        // Add new device
        ScannedDevice newDevice = new ScannedDevice(device, rssi, mCtx);
        mScannedDevices.add(newDevice);

        notifyAdapters();
    }

    public void clear(){
        mScannedDevices.clear();
    }

    public ScannedDevice getDeviceByAddress(String address){
        for(ScannedDevice device : mScannedDevices){
            if(device.mAddress.equals(address)) return device;
        }
        return null;
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

    public void disconnectAll(){
        for(ScannedDevice device : mScannedDevices){
            device.disconnect();
        }
    }

    // begin adapter handling

    public void addAdapter(LeDeviceAdapter adapter){
        mAdapters.add(adapter);
    }

    private void notifyAdapters(){
        for(LeDeviceAdapter adapter : mAdapters){
            adapter.resetData(mScannedDevices);
        }
    }

    // end adapter handling
}
