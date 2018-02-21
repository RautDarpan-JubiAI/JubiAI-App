package com.jubi.ai.chatbot.models;

import com.jubi.ai.chatbot.enums.Type;

public class ChatButton {
    private Type type;
    private String text;
    private String data;

    public ChatButton(Type type, String text, String data) {
        this.type = type;
        this.text = text;
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
