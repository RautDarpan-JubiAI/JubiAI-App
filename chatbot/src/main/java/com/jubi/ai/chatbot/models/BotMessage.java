package com.jubi.ai.chatbot.models;

import com.jubi.ai.chatbot.enums.Type;

public class BotMessage {
    private int id;
    private Type type;
    private String value;

    public BotMessage(int id, Type type, String value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
