package cas.michael.glocentral.ServerActivity;


public class Presenter implements MVP.Presenter {

    MVP.View mView;

    Presenter(MVP.View view){
        mView = view;
    }


    @Override
    public void gattEvent(int eventId) {
        mView.toggleLayoutVisibility(eventId);
    }
}

