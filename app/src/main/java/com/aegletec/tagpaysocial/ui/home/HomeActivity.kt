package com.aegletec.tagpaysocial.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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
    lateinit var realm : Realm


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        realm = Realm.getDefaultInstance()

        realm.executeTransaction { realm: Realm ->
            viewModel.getUser()
        }

        viewModel.userInfoResponse.observe(this, Observer {
           try {
               lifecycleScope.launch {
                   //  Toast.makeText(HomeActivity::class.java, it.value?.firstName.toString(), Toast.LENGTH_SHORT).show()
                   snackbar(it.firstName.toString(),findViewById(R.id.homepage))

               }
           }catch (e:Exception){

               handleAllError(findViewById(R.id.homepage),e.message)

           }

        })

    }


    override fun onDestroy() {
        super.onDestroy()
        realm.close()

    }
}