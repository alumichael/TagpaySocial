package com.aegletec.tagpaysocial.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.util.Pair
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.aegletec.tagpaysocial.data.network.Resource
import com.aegletec.tagpaysocial.ui.auth.LoginFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.logo
import kotlinx.android.synthetic.main.login_fragment.submit_btn
import kotlinx.android.synthetic.main.login_fragment.welcome

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun <A : Activity> Activity.fromSplashToLogin(activity: Class<A>) {
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
            startActivity(it, option.toBundle())
        }else{
            startActivity(it)
        }

    }
}

fun <A : Activity> Activity.fromLoginToHomeActivity(activity: Class<A>, view: View) {
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
                    pair[0], pair[1], pair[2], pair[3], pair[4], pair[5], pair[6])
            startActivity(it, option.toBundle())
          //  Navigation.findNavController(view).navigate(R.id.registerFragment)

        }else{
            startActivity(it)
        }

    }
}

fun <A : Activity> Activity.fromRegisterToHomeActivity(activity: Class<A>, view: View) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pair = listOf(
                Pair<View, String>(logo, "logo_image"),
                Pair<View, String>(welcome, "logo_text"),
                Pair<View, String>(log_prompt, "slogan_text"),
                Pair<View, String>(log_code, "code_edit_trans"),
                Pair<View, String>(log_email, "email_tran"),
                Pair<View, String>(log_password, "password_tran"),
                Pair<View, String>(reg_pin, "pin_trans"),
                Pair<View, String>(submit_btn, "button_tran"),
                Pair<View, String>(register_screen, "login_register_tran")

        )
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val option = ActivityOptions.makeSceneTransitionAnimation(this,
                    pair[0], pair[1], pair[2], pair[3], pair[4], pair[5], pair[6], pair[7], pair[8])
            startActivity(it, option.toBundle())
            //  Navigation.findNavController(view).navigate(R.id.registerFragment)

        }else{
            startActivity(it)
        }

    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}



fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}

fun Fragment.snackbar(message: String, view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()

}

fun Activity.snackbar(message: String, view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()

}

fun Fragment.handleApiError(
        failure: Resource.Failure,
        retry: (() -> Unit)? = null
) {
    when {
        failure.isNetworkError -> {requireView().snackbar(
                "Please check your internet connection",
                retry
        )

        }
        failure.errorCode == 401 -> {
            if (this is LoginFragment) {
                requireView().snackbar("You've entered incorrect email or password")
            } else {
              //  logout()
            }
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}

fun Activity.handleAllError(
        view: View,
        failure: String?,
        retry: (() -> Unit)? = null

) {
    val error = failure
    if (error != null) {
        this.snackbar(error, view)
    }
}

@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.O)
 fun Fragment.requestIMEI(): String {

    var imei_no = ""

    if (ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.READ_PHONE_STATE) !=
            PackageManager.PERMISSION_GRANTED) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.READ_PHONE_STATE)) {

        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_PHONE_STATE), 2)

        }
    }

    try {

        val androidID = Settings.Secure.getString(requireActivity().contentResolver, Settings.Secure.ANDROID_ID)


        if (androidID != null) {
            imei_no = androidID

        }
    } catch (ex: Exception) {
        //  view?.let { snackbar(ex.toString(), it) }
        Log.i("permissionError", ex.toString())

    }

    return imei_no
}


interface ItemClickListener{
    fun onItemClick(pos: Int)
}

