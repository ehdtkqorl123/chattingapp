package chatting.paulshin.ca.chattingapp.ui.main;

import java.util.ArrayList;
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
		getMvpView().showMessages(new ArrayList(Arrays.asList(
				new Message(0, "1ttestest", 100, true),
				new Message(1, "2gggdsagsafasdf", 101, false),
				new Bill(2, "3foozzz", 102, "323352 325234 32523 32", 32.15, 165.52, "02/26/16", 400.33)
		)));
	}

	public void sendMessage(String text) {
		//TODO save
		Message message = new Message(0, text, 123, true);

		getMvpView().showMessage(message);
	}
}
