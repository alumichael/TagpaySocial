package com.aegletec.tagpaysocial.data.network

import com.aegletec.tagpaysocial.data.api_models.*
import com.aegletec.tagpaysocial.data.localdb.db_model.AssignedBeneficialPaymentsItem
import com.aegletec.tagpaysocial.data.localdb.db_model.AssignedBeneficiariesItem
import com.aegletec.tagpaysocial.data.localdb.db_model.AssignedBeneficiariesLocal
import com.aegletec.tagpaysocial.data.localdb.db_model.AssignedCommunitiesItem
import io.realm.RealmList
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiInterface {


    @POST("api/v1/auth/register-device")
    suspend fun registerDevice(
           @Body registerDevice: RegisterDevice

    ):AuthResponse

    @POST("api/v1/auth/social")
    suspend fun login(
            @Body loginPost: LoginPost
    ):AuthResponse


    @GET("api/v1/social-beneficiaries/{projectUuid}/{userUuid}/{imei}/assigned-beneficiaries")
    suspend fun getAssignedBeneficials(
            @Path("projectUuid") projectUuid:String,
            @Path("userUuid") userUuid:String,
            @Path("imei") imei:String
    ):RealmList<AssignedBeneficiariesItem>



    @GET("api/v1/social-beneficiaries/{projectUuid}/{userUuid}/{imei}/assigned-communities")
    suspend fun getAssignedCommunities(
            @Path("projectUuid") projectUuid:String,
            @Path("userUuid") userUuid:String,
            @Path("imei") imei:String
    ):RealmList<AssignedCommunitiesItem>

    @GET("api/v1/social-beneficiaries/{projectUuid}/{userUuid}/{imei}/assigned-payment-schedule")
    suspend fun getAssignedBeneficialPayment(
            @Path("projectUuid") projectUuid:String,
            @Path("userUuid") userUuid:String,
            @Path("imei") imei:String
    ):RealmList<AssignedBeneficialPaymentsItem>


    @PUT("api/v1/social-beneficiaries/{projectUuid}/{userUuid}/{imei}/assigned-beneficiaries")
    suspend fun updateAssignedBeneficials(
            @Path("projectUuid") projectUuid:String,
            @Path("userUuid") userUuid:String,
            @Path("imei") imei:String,
            @Body unsyncedBeneficiaries: ArrayList<UnsyncedBeneficiaries>
    ):ResponseBody

    @PUT("api/v1/social-beneficiaries/{projectUuid}/{userUuid}/{imei}/assigned-payment-schedule")
    suspend fun updateAssignedBeneficialPayment(
            @Path("projectUuid") projectUuid:String,
            @Path("userUuid") userUuid:String,
            @Path("imei") imei:String,
            @Body unsyncedBeneficialPayments: ArrayList<UnsyncedBeneficialPayments>
    ):ResponseBody



}

