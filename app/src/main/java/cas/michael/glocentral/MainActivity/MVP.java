package cas.michael.glocentral.MainActivity;

interface MVP {

    interface Presenter {
        void onConnectButtonPressed(String deviceAddress);
    }

    interface View {
        void openActivityPassString(Class cls, String deviceAddress);
    }
}
