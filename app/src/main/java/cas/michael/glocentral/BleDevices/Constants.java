package cas.michael.glocentral.BleDevices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Constants {

    /* central connects to devices on basis of these names parsed in advertising data */
    private static final String[] DEVICES_NAMES = new String[] { "Mike1", "Mike2", "Tablet","Thingy"};
    public static final Set<String> FILTERED_NAMES = new HashSet<>(Arrays.asList(DEVICES_NAMES));
}
