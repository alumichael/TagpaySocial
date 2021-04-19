package com.aegletec.tagpaysocial.ui.home

import androidx.activity.ComponentActivity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.data.Userpreference
import com.aegletec.tagpaysocial.data.api_models.UnsyncedBeneficialPayments
import com.aegletec.tagpaysocial.data.api_models.UnsyncedBeneficiaries
import com.aegletec.tagpaysocial.data.localdb.db_model.*
import com.aegletec.tagpaysocial.data.network.Resource
import com.aegletec.tagpaysocial.data.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.internal.InjectedFieldSignature
import io.realm.Realm
import io.realm.RealmList
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject


class HomeViewModel @ViewModelInject constructor(
        private val user_repository: UserRepository,
         val userpreference: Userpreference)
    :ViewModel() {

    private val realm = Realm.getDefaultInstance()
    var boardcards = MutableLiveData<ArrayList<BoardCards>>()
    var cards = arrayListOf<BoardCards>()


    //local user data
    private val _userInfoResponse: MutableLiveData<User> = MutableLiveData()
    val userInfoResponse: LiveData<User>get() = _userInfoResponse

    /**
     * @OnlineInteraction
     *
     * */
    private val _getAssignBeneficial: MutableLiveData<Resource<RealmList<AssignedBeneficiariesItem>>> = MutableLiveData()
    val get_assigns_beneficials: LiveData<Resource<RealmList<AssignedBeneficiariesItem>>>get() = _getAssignBeneficial

    private val _getAssignCommunities: MutableLiveData<Resource<RealmList<AssignedCommunitiesItem>>> = MutableLiveData()
    val get_assigns_communities: LiveData<Resource<RealmList<AssignedCommunitiesItem>>>get() = _getAssignCommunities

    private val _getAssignBeneficialPayments: MutableLiveData<Resource<RealmList<AssignedBeneficialPaymentsItem>>> = MutableLiveData()
    val get_assigns_beneficial_payments: LiveData<Resource<RealmList<AssignedBeneficialPaymentsItem>>>get() = _getAssignBeneficialPayments

    private val _updateAssigns: MutableLiveData<Resource<ResponseBody>> = MutableLiveData()
    val update_assigns_beneficials: LiveData<Resource<ResponseBody>>get() = _updateAssigns

    fun getAssignedbeneficials(
            projectUuid: String,
            userUuid: String,
            imei: String
    ) = viewModelScope.launch {
        _getAssignBeneficial.value = Resource.Loading
        _getAssignBeneficial.value = user_repository.get_assigned_beneficials_api(projectUuid,userUuid,imei)
    }

    fun getAssignedCommunities(
            projectUuid: String,
            userUuid: String,
            imei: String
    ) = viewModelScope.launch {
        _getAssignCommunities.value = Resource.Loading
        _getAssignCommunities.value = user_repository.get_assigned_communities_api(projectUuid,userUuid,imei)
    }

    fun getAssignedBeneficialPayment(
            projectUuid: String,
            userUuid: String,
            imei: String
    ) = viewModelScope.launch {
        _getAssignBeneficialPayments.value = Resource.Loading
        _getAssignBeneficialPayments.value = user_repository.get_assigned_payments_api(projectUuid,userUuid,imei)
    }

    fun updatebeneficials(
            projectUuid:String,
            userUuid:String,
            imei:String,
            unsyncedBeneficiaries: ArrayList<UnsyncedBeneficiaries>
    ) = viewModelScope.launch {
        _updateAssigns.value = Resource.Loading
        _updateAssigns.value = user_repository.update_assigned_beneficials_api(projectUuid,userUuid,imei,unsyncedBeneficiaries)
    }

    fun updatebeneficialPayments(
            projectUuid:String,
            userUuid:String,
            imei:String,
            unsyncedBeneficialPayments: ArrayList<UnsyncedBeneficialPayments>
    ) = viewModelScope.launch {
        _updateAssigns.value = Resource.Loading
        _updateAssigns.value = user_repository.update_assigned_payments_api(projectUuid,userUuid,imei,unsyncedBeneficialPayments)
    }




    /**
    * @OfflineInteraction
     * includes the methods that handles save to beneficials, commnities and payments
     * it also includes methods that save unsynced datas
     *
    *
    * */
    fun saveAssignedBeneficials(assignedbeneficials: RealmList<AssignedBeneficiariesItem>){
        user_repository.saveAssignedBeneficials(assignedbeneficials)
    }

    fun saveAssignedCommunities(assignedbeneficials: RealmList<AssignedCommunitiesItem>){
        user_repository.saveAssignedCommunities(assignedbeneficials)
    }
    fun saveAssignedBeneficialPayment(assignedbeneficials: RealmList<AssignedBeneficialPaymentsItem>){
        user_repository.saveAssignedBeneficialPayment(assignedbeneficials)
    }


    fun saveUnsyncedBeneficialLocal(uid:String,wallet_no:String){
        user_repository.saveUnsyncedBeneficialLocal(uid,wallet_no)

    }

    fun saveUnsyncedBeneficialPayment(uid:String,householdno:String){
        user_repository.saveUnsyncedBeneficialPayment(uid,householdno)

    }

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