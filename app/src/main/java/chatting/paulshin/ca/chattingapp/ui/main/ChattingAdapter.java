package chatting.paulshin.ca.chattingapp.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import chatting.paulshin.ca.chattingapp.R;
import chatting.paulshin.ca.chattingapp.data.model.Bill;
import chatting.paulshin.ca.chattingapp.data.model.Message;

/**
 * Created by paulshin on 2017-03-25.
 */

public class ChattingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private static final int TYPE_MESSAGE = 0;
	private static final int TYPE_BILL = 1;

	private final Context context;
	private final LayoutInflater layoutInflater;

	private List<Message> messages;

	public ChattingAdapter(Context context) {
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		switch (viewType) {
			case TYPE_MESSAGE:
				return new MessageHolder(layoutInflater.inflate(
						R.layout.item_message, parent, false));
			case TYPE_BILL:
				return new BillHolder(layoutInflater.inflate(
						R.layout.item_bill, parent, false));
		}

		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		switch (getItemViewType(position)) {
			case TYPE_MESSAGE:
				bindMessage(getItem(position), (MessageHolder) holder);
				break;
			case TYPE_BILL:
				bindBill((Bill)getItem(position), (BillHolder) holder);
				break;
		}
	}

	private void bindMessage(Message message, MessageHolder holder) {
		holder.textView.setText(message.text);
	}

	private void bindBill(Bill bill, BillHolder holder) {
		holder.textView.setText(context.getString(R.string.bill_message));
		holder.text2View.setText(bill.text);
	}

	@Override
	public int getItemViewType(int position) {
		Message message = getItem(position);
		if (message instanceof Bill) {
			return TYPE_BILL;
		} else {
			return TYPE_MESSAGE;
		}
	}

	private Message getItem(int position) {
		return messages.get(position);
	}

	@Override
	public int getItemCount() {
		return 0;
	}

	static class MessageHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.text)
		TextView textView;

		MessageHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	static class BillHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.text)
		TextView textView;
		@BindView(R.id.text2)
		TextView text2View;

		BillHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
