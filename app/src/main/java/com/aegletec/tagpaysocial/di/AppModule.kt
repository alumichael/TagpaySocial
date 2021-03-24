package com.aegletec.tagpaysocial.di

import android.content.Context
import com.aegletec.tagpaysocial.data.Userpreference
import com.aegletec.tagpaysocial.data.network.ApiInterface
import com.aegletec.tagpaysocial.data.network.RetrofitClient
import com.aegletec.tagpaysocial.data.repository.AuthRepository
import com.aegletec.tagpaysocial.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    fun provideAuthRepository(
            authApi: ApiInterface,
            userPreferences: Userpreference
    ): AuthRepository {
        return AuthRepository(authApi, userPreferences)
    }

    @Provides
    fun provideUserRepository(userApi: ApiInterface): UserRepository {
        return UserRepository(userApi)
    }

}