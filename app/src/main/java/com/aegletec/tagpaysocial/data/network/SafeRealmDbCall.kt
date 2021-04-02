package com.aegletec.tagpaysocial.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception


interface SafeRealmDbCall {
    suspend fun <T> safeRealmdbCall(
        realmCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(realmCall.invoke())
            } catch (throwable: Throwable) {
                Resource.DBFailure(true,  throwable.message)
            }
        }
    }
}