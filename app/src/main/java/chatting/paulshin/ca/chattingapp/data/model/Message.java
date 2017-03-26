package chatting.paulshin.ca.chattingapp.data.model;

/**
 * Created by paulshin on 2017-03-25.
 */

public class Message {
	private int id;
	private String text;
	private long timestamp;
	private boolean isMe;

	public Message(int id, String text, long timestamp, boolean isMe) {
		this.id = id;
		this.text = text;
		this.timestamp = timestamp;
		this.isMe = isMe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
