package cas.michael.glocentral.BleDevices.GattAttribute;

import android.bluetooth.BluetoothGattService;

import cas.michael.glocentral.BleDevices.GattAttribute.Inheritance.GattAttribute;

public class Service extends GattAttribute {

    BluetoothGattService mService;

    public Service(BluetoothGattService service){
        mService = service;
    }

    @Override
    public String getType() {
        return TYPE_SERVICE;
    }
}
