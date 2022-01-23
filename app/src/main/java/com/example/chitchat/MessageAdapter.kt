package com.example.chitchat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVED = 1
    val ITEM_SENT = 2

    // below function will return an integer depending upon the view type
    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderID)) {
            // it means you are sending the message
            // you need to inflate the send view holder
            return ITEM_SENT
        }else{
            return ITEM_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        // if class in sent_view_holder so we want sent.xml layout
        // if class in receive_view_holder so we want receive.xml layout
        if (viewType == 1){
            // inflate receive
            val view: View = LayoutInflater.from(context).inflate(R.layout.recieved, parent, false)
            return receiveViewHolder(view)
        }else{
            // inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)
            return sentViewHolder(view)
        }



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position] // here we get our current message, either sent or recieved
        if(holder.javaClass == sentViewHolder::class.java){
            // do stuff for sent view holder

            val viewHolder = holder as sentViewHolder  // typecasting holder as sendViewHolder
            holder.sentMessage.text = currentMessage.message


        }else{
            // do stuff for receive view holder
            val viewHolder= holder as receiveViewHolder // typecaseting holder as revieveViewHolder
            holder.receiveMessage.text = currentMessage.message

        }

    }

    override fun getItemCount(): Int {
        return messageList.size

    }
    // we need 2 viewHolder, one for sending and one for recieving messages
    // viewHolder for sending messages

    class sentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)

    }

    class receiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_received_message)
    }
}