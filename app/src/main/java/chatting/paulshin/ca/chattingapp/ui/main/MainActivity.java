package chatting.paulshin.ca.chattingapp.ui.main;

import android.os.Bundle;
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
import chatting.paulshin.ca.chattingapp.data.model.Message;

/**
 * Created by paulshin on 2017-03-25.
 */
public class MainActivity extends AppCompatActivity implements MainMvpView {

	private static final int RESPONSE_DELAY = 1000;

	private ChattingAdapter mChattingAdapter;
	private MainPresenter mMainPresenter;

	@BindView(R.id.recycler_view)
	public RecyclerView mRecyclerView;
	@BindView(R.id.empty_text)
	public TextView mEmptyTextView;
	@BindView(R.id.message)
	public EditText mMessageView;

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

		mMainPresenter = new MainPresenter();
		mMainPresenter.attachView(this);
		mMainPresenter.loadMessages();
	}

	@Override
	protected void onDestroy() {
		mMainPresenter.detachView();
		super.onDestroy();
	}

	@OnClick(R.id.send)
	public void sendMessage(View view) {
		String text = mMessageView.getText().toString().trim();
		if (!TextUtils.isEmpty(text)) {
			mMessageView.setText("");
			mMainPresenter.sendMessage(text);
		}
	}

	@Override
	public void showMessage(Message message) {
		mChattingAdapter.addMessage(message);
		mRecyclerView.scrollToPosition(0);
		mRecyclerView.setVisibility(View.VISIBLE);
		mEmptyTextView.setVisibility(View.GONE);
	}

	@Override
	public void showResponseWithDelay(final Message message) {
		// Show a delayed response to be realistic..
		mRecyclerView.postDelayed(new Runnable() {
			@Override
			public void run() {
				mChattingAdapter.addMessage(message);
				mRecyclerView.scrollToPosition(0);
				mRecyclerView.setVisibility(View.VISIBLE);
				mEmptyTextView.setVisibility(View.GONE);
			}
		}, RESPONSE_DELAY);
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
