package com.aegletec.tagpaysocial.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aegletec.tagpaysocial.data.api_models.AuthResponse
import com.aegletec.tagpaysocial.data.api_models.RegisterDevice
import com.aegletec.tagpaysocial.data.localdb.db_model.User
import com.aegletec.tagpaysocial.data.network.Resource
import com.aegletec.tagpaysocial.data.repository.UserRepository
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @ViewModelInject constructor(
        private val user_repository: UserRepository)
    :ViewModel() {

    private val realm = Realm.getDefaultInstance()


    private val _userInfoResponse: MutableLiveData<User> = MutableLiveData()
      val userInfoResponse: LiveData<User>get() = _userInfoResponse



    fun getUser() = viewModelScope.launch {
        _userInfoResponse.value = getUser
    }


    val getUser : User? =realm.where(User::class.java).findFirstAsync()



 }