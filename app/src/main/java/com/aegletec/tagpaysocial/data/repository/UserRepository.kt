package com.aegletec.tagpaysocial.data.repository


import com.aegletec.tagpaysocial.data.Userpreference
import com.aegletec.tagpaysocial.data.api_models.AuthResponse
import com.aegletec.tagpaysocial.data.api_models.RegisterDevice
import com.aegletec.tagpaysocial.data.api_models.UnsyncedBeneficialPayments
import com.aegletec.tagpaysocial.data.api_models.UnsyncedBeneficiaries
import com.aegletec.tagpaysocial.data.localdb.RealmOperation
import com.aegletec.tagpaysocial.data.localdb.db_model.AssignedBeneficialPaymentsItem
import com.aegletec.tagpaysocial.data.localdb.db_model.AssignedBeneficiariesItem
import com.aegletec.tagpaysocial.data.localdb.db_model.AssignedBeneficiariesLocal
import com.aegletec.tagpaysocial.data.localdb.db_model.AssignedCommunitiesItem
import com.aegletec.tagpaysocial.data.network.ApiInterface
import com.aegletec.tagpaysocial.data.network.SafeApiCall
import com.aegletec.tagpaysocial.data.network.SafeRealmDbCall
import io.realm.RealmList
import javax.inject.Inject

class UserRepository @Inject constructor(
        private  val api: ApiInterface,
        private val saverealm: RealmOperation,
        private val userpreference: Userpreference
) :SafeRealmDbCall,SafeApiCall  {

    fun saveDeviceToDb_repo(user: AuthResponse) {
        //delete the former record and save the new user data into db here
        saverealm.deleteDb()
        saverealm.saveDevice(user)
    }


    fun saveAssignedBeneficials(assignedbeneficials: RealmList<AssignedBeneficiariesItem>){
        saverealm.saveAssignedBeneficials(assignedbeneficials)
    }


    fun saveAssignedCommunities(assignedbeneficials: RealmList<AssignedCommunitiesItem>){
        saverealm.saveAssignedCommunities(assignedbeneficials)
    }

    fun saveAssignedBeneficialPayment(assignedbeneficials: RealmList<AssignedBeneficialPaymentsItem>){
        saverealm.saveAssignedBeneficialPayments(assignedbeneficials)
    }

    fun saveUnsyncedBeneficialLocal(uid:String,wallet_no:String){
        saverealm.saveUnsyncedBeneficialLocal(uid,wallet_no)

    }

    fun saveUnsyncedBeneficialPayment(uid:String,householdno:String){
        saverealm.saveUnsyncedBeneficialPayment(uid,householdno)

    }



    suspend fun get_assigned_beneficials_api(
            projectUuid:String,
            userUuid:String,
            imei:String
    )=safeApiCall{
        api.getAssignedBeneficials(projectUuid,userUuid,imei)

    }

    suspend fun get_assigned_communities_api(
            projectUuid:String,
            userUuid:String,
            imei:String
    )=safeApiCall{
        api.getAssignedCommunities(projectUuid,userUuid,imei)

    }

    suspend fun get_assigned_payments_api(
            projectUuid:String,
            userUuid:String,
            imei:String
    )=safeApiCall{
        api.getAssignedBeneficialPayment(projectUuid,userUuid,imei)

    }

    suspend fun update_assigned_beneficials_api(
            projectUuid:String,
            userUuid:String,
            imei:String,
            unsyncedBeneficiaries: ArrayList<UnsyncedBeneficiaries>
    )=safeApiCall{
        api.updateAssignedBeneficials(projectUuid,userUuid,imei,unsyncedBeneficiaries)

    }


    suspend fun update_assigned_payments_api(
            projectUuid:String,
            userUuid:String,
            imei:String,
            unsyncedBeneficialPayments: ArrayList<UnsyncedBeneficialPayments>
    )=safeApiCall{
        api.updateAssignedBeneficialPayment(projectUuid,userUuid,imei,unsyncedBeneficialPayments)

    }

    suspend fun saveTotalAssignBeneficials(total_benfficials: Int) {
        userpreference.saveTotalAssignBeneficials(total_benfficials)
    }

    suspend fun saveUpdatedAssignBeneficials(updated_beneficials: Int) {
        userpreference.saveUpdatedAssignBeneficials(updated_beneficials)
    }



}