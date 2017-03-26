package chatting.paulshin.ca.chattingapp.ui.main;

import java.util.List;

import chatting.paulshin.ca.chattingapp.data.model.Message;
import chatting.paulshin.ca.chattingapp.ui.base.MvpView;

/**
 * Created by paulshin on 2017-03-25.
 */

public interface MainMvpView extends MvpView {
	void showMessage(Message message);
	void showResponseWithDelay(Message message);
	void showMessages(List<Message> messages);
	void showEmptyMessages();
	void showError();
}
