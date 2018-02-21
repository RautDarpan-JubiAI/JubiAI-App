package com.jubi.ai.chatbot.views.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jubi.ai.chatbot.R;


public class ChatMessageViewHolder extends RecyclerView.ViewHolder {

    public TextView sent;
    public ImageView image, icon;
    public LinearLayout receivedView, fieldCont;

    public ChatMessageViewHolder(View itemView) {
        super(itemView);
        if(itemView != null) {
            sent = itemView.findViewById(R.id.sent);
            icon = itemView.findViewById(R.id.icon);
            receivedView = itemView.findViewById(R.id.received_view);
            fieldCont = itemView.findViewById(R.id.field_cont);
        }
    }

}
