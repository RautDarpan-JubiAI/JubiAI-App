package com.jubi.ai.chatbot;

public class Constants {

    public interface PreferenceKeys {
        String CHATBOTCONFIG = "chatbotconfig";
        String GCM_REGISTRATION_ID = "gcm_registration_id";
    }

    public interface HTTPStatusCodes {
        int OK = 200;
        int EMPTY = 204;
        int UNAUTHORIZED = 401;
        int NOT_FOUND = 404;
    }
}
