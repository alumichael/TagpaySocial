package com.aegletec.tagpaysocial.ui

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.databinding.ActivitySplashBinding
import com.aegletec.tagpaysocial.ui.auth.AuthActivity
import com.aegletec.tagpaysocial.ui.home.HomeActivity
import com.aegletec.tagpaysocial.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var bottomAnim: Animation
    private lateinit var scaleAnim: Animation
    private lateinit var realm: Realm
    private val viewModel by viewModels<HomeViewModel>()


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

        lifecycleScope.launch{
            delay(5000)
           // val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            realm.executeTransaction { realm: Realm ->
                viewModel.getUser()
            }

        }


        viewModel.userInfoResponse.observe(this, Observer {

           // snackbar(it.firstName.toString(),findViewById(R.id.splash_page))

            if (!it.isManaged) fromSplashToLogin(AuthActivity::class.java) else  startNewActivity(HomeActivity::class.java)

        })

    }
}