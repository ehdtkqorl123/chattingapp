package chatting.paulshin.ca.chattingapp.data.model;

import com.orm.SugarRecord;

/**
 * Created by paulshin on 2017-03-25.
 */

public class Message extends SugarRecord {
	private String messageId;
	private String text;
	private boolean isMe;
	private long billId;

	public Message() {}

	public Message(String messageId, String text, boolean isMe, long billId) {
		this.messageId = messageId;
		this.text = text;
		this.isMe = isMe;
		this.billId = billId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isMe() {
		return isMe;
	}

	public void setMe(boolean me) {
		isMe = me;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}
}
