package cas.michael.glocentral.BleDevices;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Wrapper singleton class for scanned LE devices.
 */
public class ScannedDevices {

    private static final String TAG = "ScannedDevices";

    /* Singleton */
    private static ScannedDevices singleton;

    private Context mCtx;

    /* Collection of scanned devices */
    private Set<ScannedDevice> mScannedDevices = new HashSet<>();

    /**
     * Private constructor
     */
    private ScannedDevices(Context ctx){
        mCtx = ctx;
    }

    /**
     * Singleton getter method
     * @return
     */
    public static ScannedDevices getInstanceOf(Context ctx){
        if(singleton != null) return singleton;
        singleton = new ScannedDevices(ctx);
        return singleton;
    }

    /**
     * Adds device wrapper to scanned devices list if not already in list
     * @param device
     */
    public void addDevice(BluetoothDevice device, int rssi){

        //Log.d(TAG,"addDevice()");

        // If device is in list, just return, otherwise add a new device
        if(isDeviceInList(device)) return;

        Log.d(TAG,"added device : "
                + String.valueOf(device.getName() + "("+device.getAddress()+")"));

        // Add new device
        ScannedDevice newDevice = new ScannedDevice(device, rssi);
        mScannedDevices.add(newDevice);
    }

    /**
     * Returns a list of ScannedDevices type
     */
    public List<ScannedDevice> getList(){
        List list = new ArrayList<ScannedDevice>();
        for(ScannedDevice device : mScannedDevices) list.add(device);
        return list;
    }

    /**
     * Check if device is currently in scanned device list
     * @return
     */
    private boolean isDeviceInList(BluetoothDevice device){
        String address = device.getAddress();
        for(ScannedDevice scannedDevice : mScannedDevices){
            if(scannedDevice.mDevice.getAddress().equals(address)) return true;
        }
        return false;
    }

    public interface ScannedDevicesInterface {

        // Main activity
        void updateAdapter();

        // Other
    }
}
