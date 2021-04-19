package com.aegletec.tagpaysocial.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.data.Userpreference
import com.aegletec.tagpaysocial.ui.auth.AuthActivity
import com.aegletec.tagpaysocial.ui.handleAllError
import com.aegletec.tagpaysocial.ui.navigateToRegister
import com.aegletec.tagpaysocial.ui.snackbar
import com.aegletec.tagpaysocial.ui.startNewActivity
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeViewModel>()


    @Inject
    lateinit var userpreference: Userpreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        lifecycleScope.launch {
            userpreference.setLoggedin(true)
        }

    }

}