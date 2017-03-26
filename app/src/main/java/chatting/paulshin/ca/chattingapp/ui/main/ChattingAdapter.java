package chatting.paulshin.ca.chattingapp.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
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

	public void setMessages(List<Message> messages) {
		this.messages = messages;
		notifyDataSetChanged();
	}

	public void addMessage(Message message) {
		if (messages == null) {
			messages = new ArrayList<>();
		}
		messages.add(message);
		notifyItemInserted(0); // Since index is reversed, the inserted position is 0
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
		setViewMargin(holder.messageView, message.isMe());

		holder.textView.setBackgroundResource(message.isMe() ? R.drawable.message_bubble_me : R.drawable.message_bubble_other);
		holder.textView.setText(message.getText());
	}

	private void bindBill(Bill bill, BillHolder holder) {
		setViewMargin(holder.messageView, false);

		holder.textView.setText(context.getString(R.string.bill_message));
		holder.accountNumberView.setText(bill.getAccountNo());
		holder.productPriceView.setText(String.valueOf(bill.getProductPrice()));
		holder.feesView.setText(String.valueOf(bill.getFees()));
		holder.dueDateView.setText(bill.getDueDate());
		holder.totalDueView.setText(String.valueOf(bill.getTotalDue()));
	}

	private void setViewMargin(View view, boolean isMe) {
		int bubbleBigRightOrLeftMargin = context.getResources().getDimensionPixelSize(R.dimen.bubble_big_right_or_left_margin);
		int bubbleSmallRightOrLeftMargin = context.getResources().getDimensionPixelSize(R.dimen.bubble_small_right_or_left_margin);
		int bubbleTopBottomMargin = context.getResources().getDimensionPixelSize(R.dimen.bubble_top_bottom_margin);

		int leftMargin = isMe ? bubbleBigRightOrLeftMargin : bubbleSmallRightOrLeftMargin;
		int rightMargin = isMe ? bubbleSmallRightOrLeftMargin : bubbleBigRightOrLeftMargin;

		RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)view.getLayoutParams();
		params.setMargins(leftMargin, bubbleTopBottomMargin, rightMargin, bubbleTopBottomMargin);
		view.setLayoutParams(params);
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
		int reversedPosition = messages.size() - 1 - position;
		return messages.get(reversedPosition);
	}

	@Override
	public int getItemCount() {
		return messages.size();
	}

	static class MessageHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.message)
		LinearLayout messageView;
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
		@BindView(R.id.message)
		LinearLayout messageView;
		@BindView(R.id.account_number_val)
		TextView accountNumberView;
		@BindView(R.id.product_price_val)
		TextView productPriceView;
		@BindView(R.id.fees_val)
		TextView feesView;
		@BindView(R.id.due_date_val)
		TextView dueDateView;
		@BindView(R.id.total_due_val)
		TextView totalDueView;

		BillHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
