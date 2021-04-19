package com.aegletec.tagpaysocial.ui

import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.data.Userpreference
import com.aegletec.tagpaysocial.databinding.ActivitySplashBinding
import com.aegletec.tagpaysocial.ui.auth.AuthActivity
import com.aegletec.tagpaysocial.ui.home.HomeActivity
import com.aegletec.tagpaysocial.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var bottomAnim: Animation
    private lateinit var scaleAnim: Animation
    private lateinit var realm: Realm
    private val viewModel by viewModels<HomeViewModel>()

    @Inject
     lateinit var userpreference: Userpreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        realm= Realm.getDefaultInstance()


        //Animations
        scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        binding.logoSplash.animation = scaleAnim
        binding.logoSplash.animation=bottomAnim
        binding.sloganText.animation=bottomAnim

        userpreference.isLoggedIn().asLiveData().observe(this, Observer {

            Log.d("isLoggedIn",it.toString())

            lifecycleScope.launch{
                delay(5000)
                if (!it) fromSplashToLogin(AuthActivity::class.java) else  startNewActivity(HomeActivity::class.java)

                // val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
                /*realm.executeTransaction { realm: Realm ->
                    viewModel.getUser()
                }*/

            }

        })


    }

}


