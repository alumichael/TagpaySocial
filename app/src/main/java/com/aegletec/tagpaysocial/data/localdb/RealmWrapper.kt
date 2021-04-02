package com.aegletec.tagpaysocial.data.localdb

import androidx.lifecycle.LiveData
import io.realm.RealmModel
import io.realm.RealmResults

class RealmWrapper<T: RealmModel> : LiveData<RealmResults<T>>() {

}