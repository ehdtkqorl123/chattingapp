package chatting.paulshin.ca.chattingapp.ui.base;

/**
 * Created by paulshin on 2017-03-25.
 */

public interface Presenter<V extends MvpView> {
	void attachView(V mvpView);
	void detatchView();
}
