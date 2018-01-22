package cas.michael.glocentral.BleDevices.GattAttribute;

import android.bluetooth.BluetoothGattDescriptor;

import cas.michael.glocentral.BleDevices.GattAttribute.Inheritance.GattAttribute;

public class Descriptor extends GattAttribute {

    BluetoothGattDescriptor mDescriptor;

    public Descriptor(BluetoothGattDescriptor descriptor){
        mDescriptor = descriptor;
    }


    @Override
    public String getType() {
        return TYPE_DESCRIPTOR;
    }
}
