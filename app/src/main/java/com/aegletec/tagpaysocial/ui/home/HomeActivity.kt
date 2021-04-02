package com.aegletec.tagpaysocial.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.ui.handleAllError
import com.aegletec.tagpaysocial.ui.snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val realm = Realm.getDefaultInstance()


        viewModel.userInfoResponse.observe(this, Observer {
           try {
               lifecycleScope.launch {
                   //  Toast.makeText(HomeActivity::class.java, it.value?.firstName.toString(), Toast.LENGTH_SHORT).show()
                   snackbar(it.firstName.toString(),findViewById(R.id.homepage))

               }
           }catch (e:Exception){

               handleAllError(findViewById(R.id.homepage),e.message)

           }finally {

           }

        })
        realm.executeTransaction { realm: Realm ->
            viewModel.getUser()
        }

    }



}