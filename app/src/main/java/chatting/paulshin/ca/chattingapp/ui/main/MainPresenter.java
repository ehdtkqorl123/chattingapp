package chatting.paulshin.ca.chattingapp.ui.main;

import java.util.List;
import java.util.UUID;

import chatting.paulshin.ca.chattingapp.data.model.Bill;
import chatting.paulshin.ca.chattingapp.data.model.Message;
import chatting.paulshin.ca.chattingapp.ui.base.BasePresenter;

/**
 * Created by paulshin on 2017-03-25.
 */

public class MainPresenter extends BasePresenter<MainMvpView> {

	private static final String FILTER_BILL = "bill";

	public MainPresenter() {
	}

	public void loadMessages() {
		List<Message> messages = Message.listAll(Message.class);
		if (messages.isEmpty()) {
			getMvpView().showEmptyMessages();
		} else {
			getMvpView().showMessages(messages);
		}
	}

	public void sendMessage(String text) {
		Message message = new Message(UUID.randomUUID().toString(), text, true, -1);
		message.save();

		getMvpView().showMessage(message);

		createResponse(text);
	}

	private void createResponse(String text) {
		Message responseMessage;
		boolean isBill = text.toLowerCase().contains(FILTER_BILL);
		long billId = -1;

		if (isBill) {
			Bill billMessage = new Bill("32335 64235 1363 82", 32.15, 165.52, "02/26/16", 400.33); // hard-coded for now
			billId = billMessage.save();
		}

		String responseText = isBill ? null : text; // if it is bill response, no need to save text since we show predefined response
		responseMessage = new Message(UUID.randomUUID().toString(), responseText, false, billId);
		responseMessage.save();

		getMvpView().showResponseWithDelay(responseMessage);
	}
}
