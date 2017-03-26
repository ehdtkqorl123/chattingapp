package chatting.paulshin.ca.chattingapp.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import chatting.paulshin.ca.chattingapp.R;
import chatting.paulshin.ca.chattingapp.data.DataManager;
import chatting.paulshin.ca.chattingapp.data.local.DatabaseHelper;
import chatting.paulshin.ca.chattingapp.data.model.Message;

public class MainActivity extends AppCompatActivity implements MainMvpView {

	private ChattingAdapter mChattingAdapter;
	private MainPresenter mMainPresenter;

	@BindView(R.id.recycler_view)
	public RecyclerView mRecyclerView;
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
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		DatabaseHelper databaseHelper = new DatabaseHelper();
		DataManager dataManager = new DataManager(databaseHelper);
		mMainPresenter = new MainPresenter(dataManager);
	}

	@Override
	public void showMessage(Message message) {

	}

	@Override
	public void showMessages(List<Message> messages) {

	}

	@Override
	public void showEmptyMessages() {

	}

	@Override
	public void showError() {

	}
}