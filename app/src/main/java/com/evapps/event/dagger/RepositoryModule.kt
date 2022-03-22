package com.evapps.event.dagger

import com.evapps.data.local.LoginLocalDataSource
import com.evapps.data.local.PreferenceManager
import com.evapps.data.remote.LoginRemoteDataSource
import com.evapps.domain.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class RepositoryModule {
    @Provides
    fun providesLoginRepository(
        localDataSource: LoginLocalDataSource,
        remoteDataSource: LoginRemoteDataSource,
        preferenceManager: PreferenceManager,
       @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): LoginRepository {
        return LoginRepository(localDataSource, remoteDataSource, preferenceManager, ioDispatcher)
    }
}