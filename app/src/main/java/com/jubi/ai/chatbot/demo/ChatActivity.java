package com.jubi.ai.chatbot.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jubi.ai.chatbot.enums.MaterialTheme;
import com.jubi.ai.chatbot.listeners.ChatBotFragmentListener;
import com.jubi.ai.chatbot.models.ChatBotConfig;
import com.jubi.ai.chatbot.views.fragment.ChatBotFragment;

public class ChatActivity extends AppCompatActivity implements ChatBotFragmentListener {
    ChatBotConfig chatBotConfig;
    ChatBotFragment chatBotFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatBotConfig = new ChatBotConfig();
        chatBotConfig.setAppLogo(R.mipmap.ic_launcher);
        chatBotConfig.setMaterialTheme(MaterialTheme.WHITE);
        chatBotConfig.setDatabaseName("jubi_ai_db");
        chatBotFragment = ChatBotFragment.newInstance(chatBotConfig);
        ChatBotFragment.loadFragment(this, chatBotFragment, R.id.frame);
    }
}
