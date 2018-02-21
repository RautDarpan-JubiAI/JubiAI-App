package com.jubi.ai.chatbot.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jubi.ai.chatbot.R;
import com.jubi.ai.chatbot.enums.AnswerType;
import com.jubi.ai.chatbot.models.BotMessage;
import com.jubi.ai.chatbot.models.Chat;
import com.jubi.ai.chatbot.models.ChatOption;
import com.jubi.ai.chatbot.persistence.ChatMessage;
import com.jubi.ai.chatbot.views.viewholder.ChatMessageViewHolder;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageViewHolder> {

    private Context context;
    private List<ChatMessage> chatMessages;

    public ChatMessageAdapter(Context context, List<ChatMessage> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;
    }

    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_message, parent, false);
        return new ChatMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        ChatMessage chatMessage = chatMessages.get(position);
        Chat chat = ChatMessage.copyProperties(chatMessage);
        List<BotMessage> botMessages = chat.getBotMessages();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textView = (TextView) layoutInflater.inflate(R.layout.item_text, null);
        ImageView imageView = (ImageView) layoutInflater.inflate(R.layout.item_image, null);
        if (chatMessage.isIncoming()) {
            holder.sent.setVisibility(View.GONE);
            for (int i = 0; i < botMessages.size(); i++) {
                BotMessage botMessage = botMessages.get(i);
                switch (botMessage.getType()) {
                    case TEXT:
                        textView.setText(botMessage.getValue());
                        holder.fieldCont.addView(textView);
                        break;
                    case AUDIO:
                        break;
                    case IMAGE:
                        Picasso.with(context).load(botMessage.getValue()).into(imageView);
                        break;
                    case VIDEO:
                        break;
                }
            }
        } else {
            holder.sent.setText(botMessages.get(0).getValue());
            holder.receivedView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public void addItems(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
        notifyDataSetChanged();
    }

}
