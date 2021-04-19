package com.aegletec.tagpaysocial.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import com.aegletec.tagpaysocial.Constant
import com.aegletec.tagpaysocial.data.Userpreference
import com.aegletec.tagpaysocial.data.localdb.RealmOperation
import com.aegletec.tagpaysocial.data.localdb.db_model.User
import com.aegletec.tagpaysocial.data.network.ApiInterface
import com.aegletec.tagpaysocial.data.network.RetrofitClient
import com.aegletec.tagpaysocial.data.repository.AuthRepository
import com.aegletec.tagpaysocial.data.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RetrofitClient {
        return RetrofitClient()
    }

    @Singleton
    @Provides
    fun provideAuthApi(
            retrofitClient: RetrofitClient,
            @ApplicationContext context: Context
    ): ApiInterface {
        return retrofitClient.buildApi(ApiInterface::class.java, context)
    }

    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): Userpreference {
        return Userpreference(context)
    }

   /* @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(
                name = Constant.USERPREF
        )
    }*/

    @Singleton
    @Provides
    fun provideRealmData(): RealmOperation {
        return RealmOperation()
    }

    @Provides
    fun provideAuthRepository(
            authApi: ApiInterface,
            userPreferences: Userpreference
    ): AuthRepository {
        return AuthRepository(authApi, userPreferences)
    }
    @Provides

    fun provideUserRepository(
            userApi: ApiInterface,
            realm: RealmOperation,
            userpreference: Userpreference
    ): UserRepository {
        return UserRepository(userApi,realm,userpreference)
    }


}