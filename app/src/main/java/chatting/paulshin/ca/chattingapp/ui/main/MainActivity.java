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

		// Use MVP pattern for architecture.
		// We could use Dagger for dependency injection but for simplicity we just create Presenter object here
		mMainPresenter = new MainPresenter();
		mMainPresenter.attachView(this);

		// Load chat history from db if available
		mMainPresenter.loadMessages();
	}

	@Override
	protected void onDestroy() {
		mMainPresenter.detachView();
		super.onDestroy();
	}

	/**
	 * Send non-empty text input to presenter
	 * @param view
	 */
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

	/**
	 * Show a delayed response to mimic real life chat delays
	 * @param message
	 */
	@Override
	public void showResponseWithDelay(final Message message) {
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

	/**
	 * Show multiple messages loaded from db
	 * @param messages
	 */
	@Override
	public void showMessages(List<Message> messages) {
		mChattingAdapter.setMessages(messages);
		mRecyclerView.setVisibility(View.VISIBLE);
		mEmptyTextView.setVisibility(View.GONE);
	}

	/**
	 * Show empty message if there is no chat history
	 */
	@Override
	public void showEmptyMessages() {
		mRecyclerView.setVisibility(View.GONE);
		mEmptyTextView.setVisibility(View.VISIBLE);
	}

	/**
	 * Show an error message
	 */
	@Override
	public void showError() {
		Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
	}
}
