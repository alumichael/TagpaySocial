package com.aegletec.tagpaysocial.data.network

import io.realm.exceptions.RealmException
import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()

    data class DBFailure(
            val isException:  Boolean,
            val errorBody: String?
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}

