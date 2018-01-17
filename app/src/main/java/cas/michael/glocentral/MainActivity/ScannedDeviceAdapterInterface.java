package cas.michael.glocentral.MainActivity;

import java.util.ArrayList;
import java.util.List;

import cas.michael.glocentral.BleDevices.ScannedDevice;

public interface ScannedDeviceAdapterInterface {
    void resetData(ArrayList<ScannedDevice> data);
    //void reorderByRssi();
}
