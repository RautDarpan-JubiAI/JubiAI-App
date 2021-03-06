package com.jubi.ai.chatbot.models;

import java.util.List;

public class ChatOption {
    private String title;
    private String text;
    private String data;
    private String image;
    private List<ChatButton> buttons;

    public ChatOption() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ChatButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<ChatButton> buttons) {
        this.buttons = buttons;
    }
}
