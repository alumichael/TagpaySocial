package com.aegletec.tagpaysocial.ui

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.databinding.ActivitySplashBinding
import com.aegletec.tagpaysocial.ui.home.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var bottomAnim: Animation
    private lateinit var topAnim: Animation




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        binding.logoSplash.animation = topAnim
        binding.logoSplash.animation=bottomAnim
        binding.sloganText.animation=bottomAnim

        lifecycleScope.launch{
            delay(5000)
           // val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            fromSplashActivity(HomeActivity::class.java)

        }




    }
}