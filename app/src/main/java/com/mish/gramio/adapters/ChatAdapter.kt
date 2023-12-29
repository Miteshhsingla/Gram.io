package com.mish.gramio.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mish.gramio.R
import com.mish.gramio.databinding.ChatUserItemBinding
import com.mish.gramio.modelclasses.UserModel
import com.mish.gramio.ui.ChatActivity

class ChatAdapter(var context: Context, var list : ArrayList<UserModel>):RecyclerView.Adapter<ChatAdapter.ChatViewHolder>(){

    inner class ChatViewHolder(view: View):RecyclerView.ViewHolder(view){
        var binding : ChatUserItemBinding = ChatUserItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ChatViewHolder {
        return ChatViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_user_item,parent,false))
    }

    override fun onBindViewHolder(holder: ChatAdapter.ChatViewHolder, position: Int) {
        val user = list[position]
        Glide.with(context).load(user.imageUrl).into(holder.binding.userImage)
        holder.binding.username.text = user.name

        holder.itemView.setOnClickListener{
            var intent = Intent(context,ChatActivity::class.java)
            intent.putExtra("uid",user.uid)
            intent.putExtra("userImage",user.imageUrl)
            intent.putExtra("username",user.name)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

}
