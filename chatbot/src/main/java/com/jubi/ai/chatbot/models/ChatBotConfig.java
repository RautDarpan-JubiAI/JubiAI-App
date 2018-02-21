package com.jubi.ai.chatbot.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.jubi.ai.chatbot.enums.MaterialTheme;

public class ChatBotConfig implements Parcelable {

    private MaterialTheme materialTheme = MaterialTheme.WHITE;
    private int appLogo = 0;
    private String databaseName;
    private String webId;
    private String projectId;
    private boolean saveChat;

    public ChatBotConfig() {
    }

    public ChatBotConfig(MaterialTheme materialTheme, int appLogo) {
        this.materialTheme = materialTheme;
        this.appLogo = appLogo;
    }

    public MaterialTheme getMaterialTheme() {
        return materialTheme;
    }

    public void setMaterialTheme(MaterialTheme materialTheme) {
        this.materialTheme = materialTheme;
    }

    public int getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(int appLogo) {
        this.appLogo = appLogo;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public boolean isSaveChat() {
        return saveChat;
    }

    public void setSaveChat(boolean saveChat) {
        this.saveChat = saveChat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.materialTheme.name());
        dest.writeInt(this.appLogo);
        dest.writeString(this.databaseName);
        dest.writeString(this.webId);
        dest.writeString(this.projectId);
        dest.writeByte(this.saveChat ? (byte) 1 : (byte) 0);
    }

    protected ChatBotConfig(Parcel in) {
        this.materialTheme = MaterialTheme.valueOf(in.readString());
        this.appLogo = in.readInt();
        this.databaseName = in.readString();
        this.webId = in.readString();
        this.projectId = in.readString();
        this.saveChat = in.readByte() != 0;
    }

    public static final Creator<ChatBotConfig> CREATOR = new Creator<ChatBotConfig>() {
        @Override
        public ChatBotConfig createFromParcel(Parcel source) {
            return new ChatBotConfig(source);
        }

        @Override
        public ChatBotConfig[] newArray(int size) {
            return new ChatBotConfig[size];
        }
    };

    public final static class Builder{
        ChatBotConfig chatBotConfig;
        public Builder(Context context){
            chatBotConfig = new ChatBotConfig();
        }

        public ChatBotConfig setDatabaseName(String databaseName) {
            chatBotConfig.databaseName = databaseName;
            return chatBotConfig;
        }

        public ChatBotConfig setMaterialTheme(MaterialTheme materialTheme) {
            chatBotConfig.materialTheme = materialTheme;
            return chatBotConfig;
        }

        public ChatBotConfig setAppLogo(int appLogo) {
            chatBotConfig.appLogo = appLogo;
            return chatBotConfig;
        }

    }
}
