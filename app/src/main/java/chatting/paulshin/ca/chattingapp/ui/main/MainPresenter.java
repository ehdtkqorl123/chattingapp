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

	/**
	 * Load messages from db and show them on UI
	 */
	public void loadMessages() {
		List<Message> messages = Message.listAll(Message.class);
		if (messages.isEmpty()) {
			getMvpView().showEmptyMessages();
		} else {
			getMvpView().showMessages(messages);
		}
	}

	/**
	 * Save the given text to db, and then immediately display it on UI.
	 * Then create a response message based on the given text
	 * @param text
	 */
	public void sendMessage(String text) {
		Message message = new Message(UUID.randomUUID().toString(), text, true, -1);
		message.save();

		getMvpView().showMessage(message);

		createResponse(text);
	}

	/**
	 * Create a response message based on the given text.
	 * If the text contains "Bill", we create a hard-coded bill message and save it along with the text message.
	 * Then we display this bill message on UI.
	 * @param text
	 */
	private void createResponse(String text) {
		Message responseMessage;
		boolean isBill = text.toLowerCase().contains(FILTER_BILL);
		long billId = -1;

		// Create a bill data and save it on Bill table
		if (isBill) {
			Bill billMessage = new Bill("32335 64235 1363 82", 32.15, 165.52, "02/26/16", 400.33); // hard-coded for now
			billId = billMessage.save();
		}

		// Create a response Message object. If this message is a Bill message, store the id(key) of Bill for later reference
		String responseText = isBill ? null : text; // if it is bill response, no need to save text since we show predefined response text
		responseMessage = new Message(UUID.randomUUID().toString(), responseText, false, billId);
		responseMessage.save();

		getMvpView().showResponseWithDelay(responseMessage);
	}
}
