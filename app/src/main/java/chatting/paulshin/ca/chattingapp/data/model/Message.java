package chatting.paulshin.ca.chattingapp.data.model;

/**
 * Created by paulshin on 2017-03-25.
 */

public class Message {
	public int id;
	public String text;
	public long timestamp;

	public Message(int id, String text, long timestamp) {
		this.id = id;
		this.text = text;
		this.timestamp = timestamp;
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
}
