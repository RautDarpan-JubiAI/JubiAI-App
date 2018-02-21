package com.jubi.ai.chatbot.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.jubi.ai.chatbot.persistence.ChatMessage;
import com.jubi.ai.chatbot.persistence.JubiAIChatBotDatabase;
import com.jubi.ai.chatbot.persistence.PreferenceUtils;

import java.util.List;

public class ChatMessageListViewModel extends AndroidViewModel {

    private final LiveData<List<ChatMessage>> chatMessageList;

    private JubiAIChatBotDatabase jubiAIChatBotDatabase;

    public ChatMessageListViewModel(@NonNull Application application) {
        super(application);
        PreferenceUtils preferenceUtils = new PreferenceUtils(application.getApplicationContext());
        jubiAIChatBotDatabase = JubiAIChatBotDatabase.getInstance(this.getApplication(), preferenceUtils.getChatBotConfig().getDatabaseName());
        chatMessageList = jubiAIChatBotDatabase.chatMessageDao().getAllChats();
    }

    public LiveData<List<ChatMessage>> getChatMessageList() {
        return chatMessageList;
    }
}
