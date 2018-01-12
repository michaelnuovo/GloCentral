package cas.michael.glocentral.BleDevices;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;

public class GattCallback extends BluetoothGattCallback {

    public GattCallback(){

    }

    /**
     * Callback triggered as a result of a remote characteristic notification.
     * @param gatt
     * @param characteristic
     */
    @Override
    public void onCharacteristicChanged(BluetoothGatt gatt,
                                        BluetoothGattCharacteristic characteristic){

    }

    /**
     * Callback reporting the result of a characteristic read operation.
     * @param gatt
     * @param characteristic
     * @param status
     */
    @Override
    public void onCharacteristicRead(BluetoothGatt gatt,
                                     BluetoothGattCharacteristic characteristic,
                                     int status){

    }

    /**
     * Callback indicating the result of a characteristic write operation.
     * @param gatt
     * @param characteristic
     * @param status
     */
    @Override
    public void onCharacteristicWrite(BluetoothGatt gatt,
                                      BluetoothGattCharacteristic characteristic,
                                      int status){

    }

    /**
     * Callback indicating when GATT client has connected/disconnected to/from a remote GATT server.
     * @param gatt
     * @param status
     * @param newState
     */
    @Override
    public void onConnectionStateChange(BluetoothGatt gatt,
                                        int status,
                                        int newState){

    }

    /**
     * Callback reporting the result of a descriptor read operation.
     * @param gatt
     * @param descriptor
     * @param status
     */
    @Override
    public void onDescriptorRead(BluetoothGatt gatt,
                                 BluetoothGattDescriptor descriptor,
                                 int status){

    }

    /**
     * Callback indicating the result of a descriptor write operation.
     * @param gatt
     * @param descriptor
     * @param status
     */
    @Override
    public void onDescriptorWrite(BluetoothGatt gatt,
                                  BluetoothGattDescriptor descriptor,
                                  int status){

    }

    /**
     * Callback indicating the MTU for a given device connection has changed.
     * @param gatt
     * @param mtu
     * @param status
     */
    @Override
    public void onMtuChanged(BluetoothGatt gatt,
                             int mtu,
                             int status){

    }

    /**
     * Callback triggered as result of readPhy()
     * @param gatt
     * @param txPhy
     * @param rxPhy
     * @param status
     */
    @Override
    public void onPhyRead(BluetoothGatt gatt,
                          int txPhy,
                          int rxPhy,
                          int status){

    }

    /**
     * Callback triggered as result of setPreferredPhy(int, int, int), or as a result of remote
     * device changing the PHY.
     * @param gatt
     * @param txPhy
     * @param rxPhy
     * @param status
     */
    @Override
    public void onPhyUpdate(BluetoothGatt gatt,
                            int txPhy,
                            int rxPhy,
                            int status){

    }

    /**
     * Callback reporting the RSSI for a remote device connection.
     * @param gatt
     * @param rssi
     * @param status
     */
    @Override
    public void onReadRemoteRssi(BluetoothGatt gatt,
                                 int rssi,
                                 int status){

    }

    /**
     * Callback invoked when a reliable write transaction has been completed.
     * @param gatt
     * @param status
     */
    @Override
    public void onReliableWriteCompleted(BluetoothGatt gatt,
                                         int status){

    }

    /**
     * Callback invoked when the list of remote services, characteristics and descriptors for the
     * remote device have been updated, ie new services have been discovered.
     * @param gatt
     * @param status
     */
    @Override
    public void onServicesDiscovered(BluetoothGatt gatt,
                                     int status){

    }
}
