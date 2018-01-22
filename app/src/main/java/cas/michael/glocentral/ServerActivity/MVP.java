package cas.michael.glocentral.ServerActivity;

interface MVP {

    interface Presenter {
        void gattEvent(int eventId);
    }

    interface View {
        void toggleLayoutVisibility(int eventId);
    }
}
