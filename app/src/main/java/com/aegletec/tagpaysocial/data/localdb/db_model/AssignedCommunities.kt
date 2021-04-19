package com.aegletec.tagpaysocial.data.localdb.db_model


import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@RealmClass
open class AssignedCommunities : RealmObject(){
    @SerialName("assignedCommunitiesItem")
    var assignedCommunitiesItem: RealmList<AssignedCommunitiesItem> = RealmList()

}
