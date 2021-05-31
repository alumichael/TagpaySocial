package com.aegletec.tagpaysocial.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.data.Userpreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }



}