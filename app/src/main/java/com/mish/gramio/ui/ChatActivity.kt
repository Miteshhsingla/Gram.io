package com.mish.gramio.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.mish.gramio.adapters.MessageAdapter
import com.mish.gramio.databinding.ActivityChatBinding
import com.mish.gramio.modelclasses.MessageModel
import java.util.Date

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var database: FirebaseDatabase

    private lateinit var senderUid : String
    private lateinit var receiverUid : String

    private lateinit var senderRoom : String
    private lateinit var receiverRoom : String
    private lateinit var list : ArrayList<MessageModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        senderUid = FirebaseAuth.getInstance().uid.toString()
        receiverUid = intent.getStringExtra("uid")!!

        val username = intent.getStringExtra("username")
        val userImage = intent.getStringExtra("userImage")

        senderRoom = senderUid + receiverUid
        receiverRoom = receiverUid + senderUid

        list = ArrayList()

        database = FirebaseDatabase.getInstance()

        binding.username.text = username
        Glide.with(this).load(userImage).into(binding.userImage)

        binding.imageView.setOnClickListener{
            if(binding.messageBox.text.isEmpty()){
                Toast.makeText(this,"Please Enter a message",Toast.LENGTH_SHORT).show()
            }
            else{
                val message = MessageModel(binding.messageBox.text.toString(),senderUid,Date().time)

                val randomKey = database.reference.push().key

                database.reference.child("chats")
                    .child(senderRoom).child("message").child(randomKey!!)
                    .setValue(message).addOnSuccessListener {
                        database.reference.child("chats").child(receiverRoom)
                            .child("message").child(randomKey!!)
                            .setValue(message).addOnSuccessListener {
                                binding.messageBox.text = null
                                Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show()
                            }
                    }
            }
        }

        database.reference.child("chats").child(senderRoom).child("message")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    for(snapshot1 in snapshot.children) {
                        val data=  snapshot1.getValue(MessageModel::class.java)
                        list.add(data!!)
                    }

                    binding.recyclerView.adapter = MessageAdapter(this@ChatActivity,list)
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ChatActivity,"error:$error",Toast.LENGTH_SHORT).show()
                }
            })
    }
}