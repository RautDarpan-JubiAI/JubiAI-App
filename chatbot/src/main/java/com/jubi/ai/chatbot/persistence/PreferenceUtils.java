package com.jubi.ai.chatbot.persistence;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.jubi.ai.chatbot.Constants;
import com.jubi.ai.chatbot.models.ChatBotConfig;

/**
 * Created by sayagodshala on 15/05/17.
 */

public class PreferenceUtils extends PreferenceHelper {

    public PreferenceUtils(Context context) {
        super(context);
    }

    public void setChatBotConfig(ChatBotConfig chatBotConfig) {
        addPreference(Constants.PreferenceKeys.CHATBOTCONFIG, new Gson().toJson(chatBotConfig));
    }

    public ChatBotConfig getChatBotConfig() {
        ChatBotConfig chatBotConfig = new ChatBotConfig();
        String raw = getString(Constants.PreferenceKeys.CHATBOTCONFIG, "");
        if(!raw.equalsIgnoreCase("")) {
            chatBotConfig = new Gson().fromJson(raw, ChatBotConfig.class);
        }
        return chatBotConfig;
    }

    public void setGcmToken(String token) {
        addPreference(Constants.PreferenceKeys.GCM_REGISTRATION_ID, token);
    }

    public String getGCMToken() {
        String raw = getString(Constants.PreferenceKeys.GCM_REGISTRATION_ID, "");
        Log.d("GCM Token", raw);
        return raw;
    }

    public void removeUserSession() {
//        String raw = getGCMToken();
        super.clearSession();
//        setGcmToken(raw);
    }
}
