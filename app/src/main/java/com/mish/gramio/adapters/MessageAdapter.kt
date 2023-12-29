package com.mish.gramio.adapters

import android.content.Context
import android.provider.Telephony.Mms.Sent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth
import com.mish.gramio.R
import com.mish.gramio.databinding.ReceiverItemLayoutBinding
import com.mish.gramio.databinding.SentItemLayoutBinding
import com.mish.gramio.modelclasses.MessageModel

class MessageAdapter(var context: Context,var list:ArrayList<MessageModel> ):Adapter<ViewHolder>() {
    var itemSent = 1
    var itemReceived = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if(viewType==itemSent) {
            SentViewHolder(LayoutInflater.from(context).inflate(R.layout.sent_item_layout,parent,false))
        }
        else{
            ReceiverViewHolder(LayoutInflater.from(context).inflate(R.layout.receiver_item_layout,parent,false))
        }

    }


    override fun getItemViewType(position: Int): Int {
        return if(FirebaseAuth.getInstance().uid == list[position].senderId){
            return itemSent
        }
        else{
            return itemReceived
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = list[position]

        if(holder.itemViewType == itemSent){
            val viewHolder = holder as SentViewHolder
            viewHolder.binding.userMsg.text = message.message
        }
        else{
            val viewHolder = holder as ReceiverViewHolder
            viewHolder.binding.userMsg.text = message.message
        }
    }

    inner class SentViewHolder(view: View):ViewHolder(view){
        var binding = SentItemLayoutBinding.bind(view)
    }
    inner class ReceiverViewHolder(view: View):ViewHolder(view){
        var binding = ReceiverItemLayoutBinding.bind(view)
    }
}