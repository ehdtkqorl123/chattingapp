package chatting.paulshin.ca.chattingapp.data.model;

import com.orm.SugarRecord;

/**
 * Created by paulshin on 2017-03-25.
 */

public class Message extends SugarRecord {
	private int messageId;
	private String text;
	private long timestamp;
	private boolean isMe;

	public Message(int messageId, String text, long timestamp, boolean isMe) {
		this.messageId = messageId;
		this.text = text;
		this.timestamp = timestamp;
		this.isMe = isMe;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isMe() {
		return isMe;
	}

	public void setMe(boolean me) {
		isMe = me;
	}
}
