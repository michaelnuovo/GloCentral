package cas.michael.glocentral.BleDevices.GattAttribute;

import android.bluetooth.BluetoothGattCharacteristic;

import cas.michael.glocentral.BleDevices.GattAttribute.Inheritance.GattAttribute;

public class Characteristic extends GattAttribute {

    BluetoothGattCharacteristic mCharacteristic;

    public Characteristic(BluetoothGattCharacteristic service){
        mCharacteristic = service;
    }

    @Override
    public String getType() {
        return TYPE_CHARACTERISTIC;
    }


}