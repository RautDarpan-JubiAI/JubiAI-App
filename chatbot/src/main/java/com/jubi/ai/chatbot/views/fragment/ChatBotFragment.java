package com.jubi.ai.chatbot.views.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jubi.ai.chatbot.R;
import com.jubi.ai.chatbot.enums.AnswerType;
import com.jubi.ai.chatbot.enums.Type;
import com.jubi.ai.chatbot.listeners.ChatBotFragmentListener;
import com.jubi.ai.chatbot.models.BotMessage;
import com.jubi.ai.chatbot.models.Chat;
import com.jubi.ai.chatbot.models.ChatBotConfig;
import com.jubi.ai.chatbot.persistence.ChatMessage;
import com.jubi.ai.chatbot.persistence.JubiAIChatBotDatabase;
import com.jubi.ai.chatbot.persistence.PreferenceUtils;
import com.jubi.ai.chatbot.viewModel.ChatMessageListViewModel;
import com.jubi.ai.chatbot.views.adapter.ChatMessageAdapter;

import java.util.ArrayList;
import java.util.List;


public class ChatBotFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "ChatBotFragment";

    private Bundle bundle;
    public static String CHATBOT_CONFIG = "chatbot_config";
    private ChatBotFragmentListener mListener;
    private ChatBotConfig chatBotConfig;

    private View view;
    private EditText message;
    private ImageView send;
    private RecyclerView recyclerView;
    private PreferenceUtils preferenceUtils;
    private JubiAIChatBotDatabase database;
    private ChatMessageListViewModel chatMessageListViewModel;
    private ChatMessageAdapter chatMessageAdapter;

    public ChatBotFragment() {
        // Required empty public constructor
    }

    public static ChatBotFragment newInstance(ChatBotConfig chatBotConfig) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CHATBOT_CONFIG, chatBotConfig);
        ChatBotFragment fragment = new ChatBotFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ChatBotFragment newInstanceWithDefaultConfig(String databaseName) {
        Bundle bundle = new Bundle();
        ChatBotConfig chatBotConfig = new ChatBotConfig();
        chatBotConfig.setDatabaseName(databaseName);
        bundle.putParcelable(CHATBOT_CONFIG, chatBotConfig);
        ChatBotFragment fragment = new ChatBotFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static void loadFragment(FragmentActivity activity, android.support.v4.app.Fragment f, int frameId) {
        activity.getSupportFragmentManager().beginTransaction().add(frameId, f, TAG).commitAllowingStateLoss();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        bundle = getArguments();
        if (bundle != null && bundle.containsKey(CHATBOT_CONFIG)) {
            chatBotConfig = bundle.getParcelable(CHATBOT_CONFIG);
        }

        preferenceUtils = new PreferenceUtils(getActivity());
        preferenceUtils.setChatBotConfig(chatBotConfig);

        Log.d("AuthUISettings", new Gson().toJson(chatBotConfig));
        bindView();
        setClickListener();
        bindData();

        database = JubiAIChatBotDatabase.getInstance(getContext(), chatBotConfig.getDatabaseName());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChatBotFragmentListener) {
            mListener = (ChatBotFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AuthUIFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.send) {
            String messageStr = message.getText().toString();
            if (!textIsEmpty(messageStr)) {
                database.chatMessageDao().insertChat(makeChatFromMessage(messageStr));
                message.setText("");
                recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
            }
        }
    }

    private void bindView() {
        message = view.findViewById(R.id.message);
        send = view.findViewById(R.id.send);
        recyclerView = view.findViewById(R.id.recycler_view);

        chatMessageAdapter = new ChatMessageAdapter(getActivity(), new ArrayList<ChatMessage>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatMessageAdapter);
    }

    private void setClickListener() {
        send.setOnClickListener(this);
    }

    public void recallLoginView() {
        bindData();
    }

    public void bindData() {
        chatMessageListViewModel = ViewModelProviders.of(getActivity()).get(ChatMessageListViewModel.class);
        chatMessageListViewModel.getChatMessageList().observe(this, new Observer<List<ChatMessage>>() {
            @Override
            public void onChanged(@Nullable List<ChatMessage> chatMessages) {
                chatMessageAdapter.addItems(chatMessages);
                recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
            }
        });

    }


    public static boolean textIsEmpty(String value) {
        if (value == null)
            return true;
        boolean empty = false;
        String message = value.trim();
        if (TextUtils.isEmpty(message)) {
            empty = true;
        }
        boolean isWhitespace = message.matches("^\\s*$");
        if (isWhitespace) {
            empty = true;
        }
        return empty;
    }

    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static StateListDrawable selectorBackgroundColor(int normal, int pressed) {
        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setColor(normal);
        normalDrawable.setCornerRadius(8);
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setColor(pressed);
        pressedDrawable.setCornerRadius(8);
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_pressed},
                pressedDrawable);
        states.addState(new int[]{}, normalDrawable);
        return states;
    }

    private void applyTheme() {

    }

    private ChatMessage makeChatFromMessage(String msg) {
        List<BotMessage> messages = new ArrayList<>();
        messages.add(new BotMessage(0, Type.TEXT, msg));

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setProjectId(chatBotConfig.getProjectId());
        chatMessage.setWebId(chatBotConfig.getWebId());
        chatMessage.setAnswerType(AnswerType.TEXT.name());
        chatMessage.setBotMessage(new Gson().toJson(messages));
        chatMessage.setIncoming(false);
        return chatMessage;
    }

}
