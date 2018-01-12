package cas.michael.glocentral.BleDevices;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;

public class ScannedDevice {

    private BluetoothGatt mServer;
    public BluetoothDevice mDevice;

    public String mName;
    public String mAddress;
    public int mRssi;

    public ScannedDevice(BluetoothDevice device, int rssi){
        //mServer = server;
        mDevice = device;

        mName = device.getName();
        mAddress = device.getAddress();
        mRssi = rssi;
    }

    public void connect(Context ctx){

        mDevice.connectGatt(ctx,false,new GattCallback());
    }
}
