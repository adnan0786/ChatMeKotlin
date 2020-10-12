package com.example.chatmekotlin.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.chatmekotlin.Fragment.ChatFragment
import com.example.chatmekotlin.Fragment.ContactFragment
import com.example.chatmekotlin.Fragment.ProfileFragment
import com.example.chatmekotlin.R
import com.example.chatmekotlin.Util.AppUtil
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoard : AppCompatActivity() {

    private var fragment: Fragment? = null
    private lateinit var appUtil: AppUtil

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        appUtil= AppUtil()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.dashboardContainer, ChatFragment()).commit()
            bottomChip.setItemSelected(R.id.btnChat)
        }
        bottomChip.setOnItemSelectedListener { id ->
            when (id) {
                R.id.btnChat -> {
                    fragment = ChatFragment()


                }

                R.id.btnProfile -> {
                    fragment = ProfileFragment();

                }

                R.id.btnContact -> fragment = ContactFragment()
            }

            fragment!!.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.dashboardContainer, fragment!!)
                    .commit()
            }
        }

    }

    override fun onPause() {
        super.onPause()
        appUtil.updateOnlineStatus("offline")
    }

    override fun onResume() {
        super.onResume()
        appUtil.updateOnlineStatus("online")
    }

}



