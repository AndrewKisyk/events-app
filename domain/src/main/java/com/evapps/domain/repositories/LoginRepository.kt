package com.evapps.domain.repositories

import androidx.lifecycle.MediatorLiveData

import com.evapps.data.local.LoginLocalDataSource
import com.evapps.data.local.PreferenceManager
import com.evapps.data.local.Result
import com.evapps.data.models.LoggedInUser
import com.evapps.data.remote.LoginRemoteDataSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository constructor(
    private val localDataSource: LoginLocalDataSource,
    private val remoteDataSource: LoginRemoteDataSource,
    private val preferenceManager: PreferenceManager,
    private val ioDispatcher: CoroutineDispatcher
) {

    // in-memory cache of the loggedInUser object
    var user = MediatorLiveData<LoggedInUser>()
        private set

    fun getNullUser(): LoggedInUser? {
        user.value = LoggedInUser("", "", "")
        return user.value
    }

    suspend fun logout()  {

    }

    suspend fun login(email: String, password: String): Result<LoggedInUser> =
        withContext(ioDispatcher) {
            val user = remoteDataSource.login(email, password)
                .doOnSuccess {
                    delay(3000)
                    localDataSource.login(it)
                    preferenceManager.userId = it.userId
                }
            user
        }


    //sign up will return the same as log in because we don't need special data
    suspend fun signup(userName: String, email: String, password: String): Result<LoggedInUser> =
        withContext(ioDispatcher) {
            val user = remoteDataSource.signup(userName, email, password)
                .doOnSuccess {
                    delay(3000)
                    localDataSource.signup(it)
                    preferenceManager.userId = it.userId
                }
            user
        }

    suspend fun getCurrentUser(): Result<LoggedInUser> =
        withContext(ioDispatcher) {
            localDataSource.getUser(preferenceManager.userId)
        }

    private fun refreshUser() {
      
    }

    companion object {
        val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }

}