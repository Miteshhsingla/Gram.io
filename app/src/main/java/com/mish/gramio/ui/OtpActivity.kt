package com.mish.gramio.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.mish.gramio.ProfileActivity
import com.mish.gramio.R
import com.mish.gramio.databinding.ActivityLoginBinding
import com.mish.gramio.databinding.ActivityOtpBinding

class OtpActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOtpBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var verificationID : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val phoneNumber = "+91" + intent.getStringExtra("phone")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, java.util.concurrent.TimeUnit.SECONDS)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    TODO("Not yet implemented")
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Toast.makeText(this@OtpActivity,"Pls try again",Toast.LENGTH_SHORT).show()
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)
                    verificationID = p0
                }

            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)

        binding.button.setOnClickListener{
            val credential = PhoneAuthProvider.getCredential(verificationID,binding.otp.text!!.toString())

            auth.signInWithCredential(credential)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        startActivity(Intent(this,ProfileActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this, "Error:${it.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}