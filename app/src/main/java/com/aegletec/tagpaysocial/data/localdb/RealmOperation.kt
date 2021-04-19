package com.aegletec.tagpaysocial.data.localdb

import android.util.Log
import com.aegletec.tagpaysocial.data.api_models.AssignedBeneficiaries
import com.aegletec.tagpaysocial.data.api_models.AuthResponse
import com.aegletec.tagpaysocial.data.localdb.db_model.*
import io.realm.Realm
import io.realm.RealmList
import org.bson.types.ObjectId
import javax.inject.Inject


class RealmOperation @Inject constructor(){

    private val realm = Realm.getDefaultInstance()
    val assignedBeneficiariesItem: RealmList<AssignedBeneficiariesItem> = RealmList()
    val assignedCommunitiesItem: RealmList<AssignedCommunitiesItem> = RealmList()
    val assignedBeneficialPaymentsItem: RealmList<AssignedBeneficialPaymentsItem> = RealmList()

    val id=ObjectId()
     fun saveDevice(user: AuthResponse){
        realm.executeTransaction { realm: Realm ->
            val saveUser=realm.createObject(User::class.java, id)
            saveUser.bvn=user.bvn
            saveUser.createdAt=user.createdAt
            saveUser.email=user.email
            saveUser.expiresIn=user.expiresIn
            saveUser.firstName=user.firstName
            saveUser.lastLoggedIn=user.lastLoggedIn
            saveUser.lastName=user.lastName
            saveUser.level=user.level
            saveUser.mobileNumber=user.mobileNumber
           // userObject.permissions= user.permissions as RealmList<String>
            saveUser.phoneNumber=user.phoneNumber
            saveUser.projectUuid=user.projectUuid
          //  userObject.roles=user.roles as RealmList<String>
            saveUser.token=user.token
            saveUser.uuid=user.uuid

        }
    }
    fun deleteDb(){
        realm.executeTransaction { realm: Realm ->
            realm.deleteAll()
        }
    }

     fun saveAssignedBeneficials(assignedbeneficials: RealmList<AssignedBeneficiariesItem>){
        try {
            realm.executeTransaction { realm: Realm ->
                assignedBeneficiariesItem.clear()
                assignedBeneficiariesItem.addAll(assignedbeneficials)

                val assignedbeneficial=realm.createObject(AssignedBeneficiariesLocal::class.java)
                assignedbeneficial.assignedBeneficiariesItem.addAll(assignedBeneficiariesItem)
            }
        }catch (e:Exception){

            Log.d("dbError",e.message.toString())
        }

     }


    fun saveAssignedCommunities(assignedcommunities: RealmList<AssignedCommunitiesItem>){
        try {
            realm.executeTransaction { realm: Realm ->
                assignedCommunitiesItem.clear()
                assignedCommunitiesItem.addAll(assignedcommunities)

                val assignedcommunities=realm.createObject(AssignedCommunities::class.java)
                assignedcommunities.assignedCommunitiesItem.addAll(assignedCommunitiesItem)
            }
        }catch (e:Exception){

            Log.d("dbError",e.message.toString())
        }

    }



    fun saveAssignedBeneficialPayments(assignedpayments: RealmList<AssignedBeneficialPaymentsItem>){
        try {
            realm.executeTransaction { realm: Realm ->
                assignedBeneficialPaymentsItem.clear()
                assignedBeneficialPaymentsItem.addAll(assignedpayments)

                val assignedpayment=realm.createObject(AssignedBeneficialPayments::class.java)
                assignedpayment.assignedBeneficialPaymentsItem.addAll(assignedBeneficialPaymentsItem)
            }
        }catch (e:Exception){

            Log.d("dbError",e.message.toString())
        }

    }

     fun saveUnsyncedBeneficialLocal(uuid: String, walletNumber: String){
         realm.executeTransaction { realm: Realm ->
             val unsyncedbeneficial=realm.createObject(UnsyncedBeneficialLocal::class.java, id)
             unsyncedbeneficial.uuid=uuid
             unsyncedbeneficial.walletNumber=walletNumber
         }

     }

    fun saveUnsyncedBeneficialPayment(uuid: String, houseHoldNo: String){
        realm.executeTransaction { realm: Realm ->
            val unsyncedbeneficial=realm.createObject(UnsyncedBeneficialPayments::class.java, id)
            unsyncedbeneficial.houseHoldNo=houseHoldNo
            unsyncedbeneficial.uuid=uuid

        }

    }


}