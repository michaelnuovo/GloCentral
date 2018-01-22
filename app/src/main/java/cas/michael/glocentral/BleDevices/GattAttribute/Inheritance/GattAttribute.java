package cas.michael.glocentral.BleDevices.GattAttribute.Inheritance;

import java.util.UUID;

public abstract class GattAttribute {

    public static final String TYPE_SERVICE = "Service";
    public static final String TYPE_CHARACTERISTIC = "Characteristic";
    public static final String TYPE_DESCRIPTOR = "Descriptor";

    public UUID mUuid;

    public abstract String getType();

    public String getUuid(){
        return mUuid == null ? "Unknown UUID" : String.valueOf(mUuid.toString());
    }
}
