package com.aegletec.tagpaysocial.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.View
import com.aegletec.tagpaysocial.databinding.ActivitySplashBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_splash.*

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun <A : Activity> Activity.fromSplashActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pair = listOf(
                Pair<View, String>(logo_splash, "logo_image"),
                Pair<View, String>(logo_text, "logo_text"),
                Pair<View, String>(slogan_text, "slogan_text")
        )


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val option = ActivityOptions.makeSceneTransitionAnimation(this,
                    pair[0], pair[1], pair[2])
            startActivity(it,option.toBundle())
        }else{
            startActivity(it)
        }




    }
}