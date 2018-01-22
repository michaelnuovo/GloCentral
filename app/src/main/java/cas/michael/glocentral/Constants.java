package cas.michael.glocentral;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cas.michael.glocentral.MainActivity.MainActivity;

public class Constants {

    // Keys
    public static final String KEY_DEVICE_ADDRESS = "KEY_DEVICE_ADDRESS";
    public static final String KEY_BLUETOOTH_PROFILE_EVENT = "KEY_BLUETOOTH_PROFILE_EVENT";
    public static final String KEY_BLUETOOTH_GATT_EVENT = "KEY_BLUETOOTH_GATT_EVENT";

    // Actions
    public static final String ACTION_BLUETOOTH_PROFILE_EVENT = "ACTION_BLUETOOTH_PROFILE_EVENT";
    public static final String ACTION_BLUETOOTH_GATT_EVENT = "ACTION_BLUETOOTH_GATT_EVENT";

    // Device name filter
    public static final Set<String> DEVICE_FILTER =
            new HashSet<>(Arrays.asList(new String[] { "Mike1", "Mike2", "Tablet","Thingy"}));
}
