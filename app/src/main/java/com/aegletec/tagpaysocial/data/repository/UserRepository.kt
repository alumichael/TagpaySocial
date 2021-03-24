package com.aegletec.tagpaysocial.data.repository

import com.aegletec.tagpaysocial.data.network.ApiInterface
import com.aegletec.tagpaysocial.data.network.SafeApiCall
import javax.inject.Inject

class UserRepository @Inject constructor(
        api: ApiInterface
) : SafeApiCall {

}