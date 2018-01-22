package cas.michael.glocentral.MainActivity;

import cas.michael.glocentral.ServerActivity.ServerActivity;

public class Presenter implements MVP.Presenter {

    MVP.View mView;

    Presenter(MVP.View view){
        mView = view;
    }

    @Override
    public void onConnectButtonPressed(String deviceAddress) {
        mView.openActivityPassString(ServerActivity.class, deviceAddress);
    }
}
