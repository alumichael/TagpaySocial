package com.aegletec.tagpaysocial.data.localdb.db_model


import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.serialization.SerialName

@RealmClass
open class AssignedBeneficialPayments : RealmObject(){
    @SerialName("assignedBeneficialPaymentsItem")
    var assignedBeneficialPaymentsItem: RealmList<AssignedBeneficialPaymentsItem> = RealmList()
}