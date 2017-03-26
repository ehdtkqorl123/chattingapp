package chatting.paulshin.ca.chattingapp.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chatting.paulshin.ca.chattingapp.R;
import chatting.paulshin.ca.chattingapp.data.DataManager;
import chatting.paulshin.ca.chattingapp.data.local.DatabaseHelper;
import chatting.paulshin.ca.chattingapp.data.model.Message;

public class MainActivity extends AppCompatActivity implements MainMvpView {

	private ChattingAdapter mChattingAdapter;
	private MainPresenter mMainPresenter;

	@BindView(R.id.recycler_view)
	public RecyclerView mRecyclerView;
	@BindView(R.id.empty_text)
	public TextView mEmptyTextView;
	@BindView(R.id.message)
	public EditText mMessageView;
	@BindView(R.id.send)
	public FloatingActionButton mSendView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		mChattingAdapter = new ChattingAdapter(this);
		mRecyclerView.setAdapter(mChattingAdapter);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		linearLayoutManager.setReverseLayout(true);
		mRecyclerView.setLayoutManager(linearLayoutManager);

		DatabaseHelper databaseHelper = new DatabaseHelper();
		DataManager dataManager = new DataManager(databaseHelper);
		mMainPresenter = new MainPresenter(dataManager);

		mMainPresenter.attachView(this);
		mMainPresenter.loadMessages();
	}

	@OnClick(R.id.send)
	public void sendMessage(View view) {
		String text = mMessageView.getText().toString().trim();
		if (!TextUtils.isEmpty(text)) {
			mMessageView.setText("");
			mMainPresenter.sendMessage(text);
			mRecyclerView.scrollToPosition(0);
		}
	}

	@Override
	public void showMessage(Message message) {
		mChattingAdapter.addMessage(message);
		mRecyclerView.setVisibility(View.VISIBLE);
		mEmptyTextView.setVisibility(View.GONE);
	}

	@Override
	public void showMessages(List<Message> messages) {
		mChattingAdapter.setMessages(messages);
		mRecyclerView.setVisibility(View.VISIBLE);
		mEmptyTextView.setVisibility(View.GONE);
	}

	@Override
	public void showEmptyMessages() {
		mRecyclerView.setVisibility(View.GONE);
		mEmptyTextView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showError() {
		Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
	}
}
