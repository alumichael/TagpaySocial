package com.aegletec.tagpaysocial.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.databinding.ActivitySplashBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_splash.logo_splash
import kotlinx.android.synthetic.main.login_fragment.*

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

fun <A : Activity> Activity.fromLoginActivity(activity: Class<A>,view: View) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pair = listOf(
                Pair<View, String>(logo, "logo_image"),
                Pair<View, String>(welcome, "logo_text"),
                Pair<View, String>(log_prompt, "slogan_text"),
                Pair<View, String>(log_email, "email_tran"),
                Pair<View, String>(log_password, "password_tran"),
                Pair<View, String>(submit_btn, "button_tran"),
                Pair<View, String>(register_screen, "login_register_tran")

        )
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val option = ActivityOptions.makeSceneTransitionAnimation(this,
                    pair[0], pair[1], pair[2],pair[3],pair[4],pair[5],pair[6])
            startActivity(it,option.toBundle())
            Navigation.findNavController(view).navigate(R.id.registerFragment)

        }else{
            startActivity(it)
        }

    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

/*fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}*/

fun Fragment.snackbar(message: String,view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()

}
