package com.mish.gramio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mish.gramio.databinding.ActivityLoginBinding
import com.mish.gramio.ui.OtpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        binding.button.setOnClickListener{
            if(binding.fieldPhone.text!!.isEmpty()){
                Toast.makeText(this, "Please enter your contact number", Toast.LENGTH_SHORT).show()
            }
            else{
                var intent = Intent(this,OtpActivity::class.java)
                intent.putExtra("phone",binding.fieldPhone.text!!.toString())
                startActivity(intent)
            }
        }

    }
}