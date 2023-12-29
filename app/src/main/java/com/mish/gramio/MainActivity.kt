package com.mish.gramio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.mish.gramio.adapters.ViewPagerAdapter
import com.mish.gramio.databinding.ActivityMainBinding
import com.mish.gramio.ui.CallsFragment
import com.mish.gramio.ui.ChatFragment
import com.mish.gramio.ui.StatusFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        var FragmentArrayList = ArrayList<Fragment>()


        FragmentArrayList.add(ChatFragment())
        FragmentArrayList.add(StatusFragment())
        FragmentArrayList.add(CallsFragment())

        val adapter = ViewPagerAdapter(this,supportFragmentManager,FragmentArrayList)
        binding!!.viewPager.adapter = adapter
        binding!!.tabs.setupWithViewPager(binding!!.viewPager)

    }
}