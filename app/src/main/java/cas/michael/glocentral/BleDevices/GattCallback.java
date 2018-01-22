package cas.michael.glocentral.BleDevices;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cas.michael.glocentral.Constants;

public class GattCallback extends BluetoothGattCallback {

    private static final String TAG = GattCallback.class.getSimpleName();

    Context mCtx;

    public GattCallback(Context ctx) {
        mCtx = ctx;
    }

    /**
     * Callback triggered as a result of a remote characteristic notification.
     *
     * @param gatt
     * @param characteristic
     */
    @Override
    public void onCharacteristicChanged(BluetoothGatt gatt,
                                        BluetoothGattCharacteristic characteristic) {

    }

    /**
     * Callback reporting the result of a characteristic read operation.
     *
     * @param gatt
     * @param characteristic
     * @param status
     */
    @Override
    public void onCharacteristicRead(BluetoothGatt gatt,
                                     BluetoothGattCharacteristic characteristic,
                                     int status) {

    }

    /**
     * Callback indicating the result of a characteristic write operation.
     *
     * @param gatt
     * @param characteristic
     * @param status
     */
    @Override
    public void onCharacteristicWrite(BluetoothGatt gatt,
                                      BluetoothGattCharacteristic characteristic,
                                      int status) {

    }

    /**
     * Callback indicating when GATT client has connected/disconnected to/from a remote GATT server.
     * @param gatt
     * @param status int: Status of the connect or disconnect operation. GATT_SUCCESS if the operation succeeds.
     * @param newState int: Returns the new connection state. Can be one of STATE_DISCONNECTED or STATE_CONNECTED
     * BluetoothGatt.GATT_SUCCESS = 0                
     * BluetoothProfile.STATE_DISCONNECTED = 0
     * BluetoothProfile.STATE_CONNECTED = 2               
     */
    @Override
    public void onConnectionStateChange(BluetoothGatt gatt,
                                        int status,
                                        int newState) {

        Log.i(TAG,"onConnectionStateChange"); // GATT_SUCCESS = 0
        Log.i(TAG,"status = "+String.valueOf(status));
        Log.i(TAG,"newState = "+String.valueOf(newState)); // STATE_DISCONNECTED = 0, STATE_CONNECTED = 2

        broadCastBluetoothProfileEvent(newState);

        if(newState == BluetoothProfile.STATE_CONNECTED){
            gatt.discoverServices();
        }
    }

    /**
     * Callback reporting the result of a descriptor read operation.
     *
     * @param gatt
     * @param descriptor
     * @param status
     */
    @Override
    public void onDescriptorRead(BluetoothGatt gatt,
                                 BluetoothGattDescriptor descriptor,
                                 int status) {

    }

    /**
     * Callback indicating the result of a descriptor write operation.
     *
     * @param gatt
     * @param descriptor
     * @param status
     */
    @Override
    public void onDescriptorWrite(BluetoothGatt gatt,
                                  BluetoothGattDescriptor descriptor,
                                  int status) {

    }

    /**
     * Callback indicating the MTU for a given device connection has changed.
     *
     * @param gatt
     * @param mtu
     * @param status
     */
    @Override
    public void onMtuChanged(BluetoothGatt gatt,
                             int mtu,
                             int status) {

    }

    /**
     * Callback triggered as result of readPhy()
     *
     * @param gatt
     * @param txPhy
     * @param rxPhy
     * @param status
     */
    @Override
    public void onPhyRead(BluetoothGatt gatt,
                          int txPhy,
                          int rxPhy,
                          int status) {

    }

    /**
     * Callback triggered as result of setPreferredPhy(int, int, int), or as a result of remote
     * device changing the PHY.
     *
     * @param gatt
     * @param txPhy
     * @param rxPhy
     * @param status
     */
    @Override
    public void onPhyUpdate(BluetoothGatt gatt,
                            int txPhy,
                            int rxPhy,
                            int status) {

    }

    /**
     * Callback reporting the RSSI for a remote device connection.
     *
     * @param gatt
     * @param rssi
     * @param status
     */
    @Override
    public void onReadRemoteRssi(BluetoothGatt gatt,
                                 int rssi,
                                 int status) {

    }

    /**
     * Callback invoked when a reliable write transaction has been completed.
     *
     * @param gatt
     * @param status
     */
    @Override
    public void onReliableWriteCompleted(BluetoothGatt gatt,
                                         int status) {

    }

    /**
     * Callback invoked when the list of remote services, characteristics and descriptors for the
     * remote device have been updated, ie new services have been discovered.
     *
     * @param gatt BluetoothGatt: GATT client invoked discoverServices()
     * @param status int: GATT_SUCCESS if the remote device has been explored successfully.
     */
    @Override
    public void onServicesDiscovered(BluetoothGatt gatt,
                                     int status) {

        Log.i(TAG,"onServicesDiscovered()");
        Log.i(TAG,"status = "+String.valueOf(status));
        if(status == BluetoothGatt.GATT_SUCCESS){

            broadCastBluetoothGattEvent(status);
        }
    }

    private void broadCastBluetoothProfileEvent(int event) {

        Log.i(TAG,"broadCastBluetoothProfileEvent()");

        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_BLUETOOTH_PROFILE_EVENT);
        intent.putExtra(Constants.KEY_BLUETOOTH_PROFILE_EVENT, event);
        mCtx.sendBroadcast(intent);
    }

    private void broadCastBluetoothGattEvent(int status){

        Log.i(TAG,"broadCastBluetoothGattEvent()");

        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_BLUETOOTH_GATT_EVENT);
        intent.putExtra(Constants.KEY_BLUETOOTH_PROFILE_EVENT, status);
        mCtx.sendBroadcast(intent);
    }
}