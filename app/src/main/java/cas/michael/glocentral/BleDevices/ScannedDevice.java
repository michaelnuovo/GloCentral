package cas.michael.glocentral.BleDevices;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cas.michael.glocentral.BleDevices.GattAttribute.Characteristic;
import cas.michael.glocentral.BleDevices.GattAttribute.Descriptor;
import cas.michael.glocentral.BleDevices.GattAttribute.Inheritance.GattAttribute;
import cas.michael.glocentral.BleDevices.GattAttribute.Service;

public class ScannedDevice {

    private static final String TAG = "ScannedDevice";

    private BluetoothGatt mGatt;
    public BluetoothDevice mDevice;

    public String mName;
    public String mAddress;
    public int mRssi;

    private List<GattAttribute> mAttributes = new ArrayList<>();

    private Context mCtx;

    public ScannedDevice(BluetoothDevice device, int rssi, Context ctx){

        mCtx = ctx;
        mDevice = device;
        mName = device.getName() == null ? "Unknown Device" : device.getName();
        mAddress = device.getAddress();
        mRssi = rssi;
    }

    public void connect(){
        mGatt = mDevice.connectGatt(mCtx,false,new GattCallback(mCtx));
    }

    public void disconnect(){
        mGatt.disconnect();
    }

    public List<GattAttribute> getAttributes(){

        //if(mAttributes != null) return mAttributes;
        initAttributes();
        return mAttributes;
    }

    private void initAttributes(){

        Log.i(TAG,"initAttributes");
        Log.i(TAG,"mGatt.getServices() = "+String.valueOf(mGatt.getServices() ));

        if(mGatt.getServices() == null) return; // safety check

        Log.i(TAG,"mGatt.getServices().size() = "+String.valueOf(mGatt.getServices().size()));

        // Add services
        for(BluetoothGattService servicePointer : mGatt.getServices()){
            Service service = new Service(servicePointer);
            service.mUuid =  servicePointer.getUuid();
            mAttributes.add(new Service(servicePointer));

            Log.i(TAG,"service UUID = "+service.mUuid);

            // Add characteristics
            for(BluetoothGattCharacteristic charPointer : servicePointer.getCharacteristics()){
                Characteristic characteristic = new Characteristic(charPointer);
                characteristic.mUuid =  charPointer.getUuid();
                //characteristic.mUuid = servicePointer.getUuid();
                mAttributes.add(characteristic);

                Log.i(TAG,"characteristic UUID = "+characteristic.mUuid);

                // Add descriptors
                for(BluetoothGattDescriptor descPointer : charPointer.getDescriptors()){
                    Descriptor descriptor = new Descriptor(descPointer);
                    descriptor.mUuid =  descPointer.getUuid();

                    Log.i(TAG,"descriptor UUID = "+descriptor.mUuid);

                    //descriptor.mUuid = servicePointer.getUuid();
                    mAttributes.add(descriptor);
                }
            }
        }

        Log.i(TAG,"mAttributes.size() = "+String.valueOf(mAttributes.size()));
    }

    public List<BluetoothGattService> getServices(){
        return mGatt.getServices();
    }
}
