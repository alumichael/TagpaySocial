package com.aegletec.tagpaysocial.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.data.api_models.AuthResponse
import com.aegletec.tagpaysocial.data.api_models.RegisterDevice
import com.aegletec.tagpaysocial.data.localdb.db_model.BoardCards
import com.aegletec.tagpaysocial.data.localdb.db_model.User
import com.aegletec.tagpaysocial.data.network.Resource
import com.aegletec.tagpaysocial.data.repository.UserRepository
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get
import javax.inject.Inject

class HomeViewModel @ViewModelInject constructor(
        private val user_repository: UserRepository)
    :ViewModel() {

    private val realm = Realm.getDefaultInstance()
   // var cards = arrayListOf<BoardCards>()

    //user data
    private val _userInfoResponse: MutableLiveData<User> = MutableLiveData()
    val userInfoResponse: LiveData<User>get() = _userInfoResponse
    //card data
  /*  private var _boardcards: MutableLiveData<ArrayList<BoardCards>> = MutableLiveData()
    val boardcards:LiveData<ArrayList<BoardCards>>get() = _boardcards*/

    var boardcards = MutableLiveData<ArrayList<BoardCards>>()
    var cards = arrayListOf<BoardCards>()


    fun getUser() = viewModelScope.launch {
        _userInfoResponse.value = getUser

    }

    val getUser : User? =realm.where(User::class.java).findFirstAsync()

    fun getBoardcardsViewModel()=viewModelScope.launch {
        boardcards.value=getBoardcards()
    }

    fun getBoardcards():ArrayList<BoardCards> {
        cards.clear()

        cards.add(0, BoardCards("Assign NFC", R.drawable.assign_nfc))
        cards.add(1, BoardCards("Make Payment", R.drawable.make_payment))
        cards.add(2, BoardCards("Transaction History", R.drawable.payment_history))
        cards.add(3, BoardCards("Sync Status", R.drawable.sync))
        cards.add(4, BoardCards("Settings", R.drawable.setting))
        cards.add(5, BoardCards("Profile", R.drawable.profile))
        return cards

    }








 }