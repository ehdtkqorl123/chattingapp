package chatting.paulshin.ca.chattingapp.ui.main;

import java.util.Arrays;

import chatting.paulshin.ca.chattingapp.data.DataManager;
import chatting.paulshin.ca.chattingapp.data.model.Bill;
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
		getMvpView().showMessages(Arrays.asList(
				new Message(0, "ttestest", 100, true),
				new Message(1, "gggdsagsafasdf", 101, false),
				new Bill(2, "foozzz", 102, "323352 325234 32523 32", 32.15, 165.52, "02/26/16", 400.33)
		));
	}

	public void sendMessage(Message message) {
		//TODO save
		getMvpView().showMessage(message);
	}
}
