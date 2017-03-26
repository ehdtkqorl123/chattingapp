package chatting.paulshin.ca.chattingapp.ui.main;

import chatting.paulshin.ca.chattingapp.data.DataManager;
import chatting.paulshin.ca.chattingapp.data.model.Message;
import chatting.paulshin.ca.chattingapp.ui.base.BasePresenter;

/**
 * Created by paulshin on 2017-03-25.
 */

public class MainPresenter extends BasePresenter<MainMvpView> {

	private final DataManager mDataManager;

	public MainPresenter(DataManager dataManager) {
		mDataManager = dataManager;
	}

	public void loadMessages() {

	}

	public void sendMessage(Message message) {
		//TODO save
		getMvpView().showMessage(message);
	}
}
