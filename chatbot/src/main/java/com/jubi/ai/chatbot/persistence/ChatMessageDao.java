package com.jubi.ai.chatbot.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ChatMessageDao {
    @Query("SELECT * FROM chat")
    LiveData<List<ChatMessage>> getAllChats();

    @Insert
    void insertChat(ChatMessage... chats);
}
