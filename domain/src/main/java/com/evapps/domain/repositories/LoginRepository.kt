package com.evapps.domain.repositories

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import com.evapps.data.database.UserDao

import com.evapps.data.local.LoginDataSource
import com.evapps.data.local.Result
import com.evapps.data.models.LoggedInUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
@Singleton
class LoginRepository @Inject constructor(val dataSource: LoginDataSource,
val userDao: UserDao) {

    // in-memory cache of the loggedInUser object
    var user = MediatorLiveData<LoggedInUser>()
        private set


    lateinit var userFromDb: Disposable

    val isLoggedIn: Boolean
        get() = user.value != null

    fun getNullUser(): LoggedInUser? {
        user.value = LoggedInUser("", "","")
        return user.value
    }
    fun logout() {
        user.value = null
        dataSource.logout()
        userFromDb.dispose()
    }

    fun getUser(userId: String): LoggedInUser {
        refreshUser(userId)
        // Returns a Flow object directly from the database.
        return userDao.load(userId)
    }

    fun login(email: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = dataSource.login(email, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }
        return result
    }

    //sign up will return the same as log in because we don't need special data
    fun signup(username: String, email: String, password: String): Result<LoggedInUser> {
        val result = dataSource.signup(username, email, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }
        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        user.value = loggedInUser
        Single.just(userDao)
            .subscribeOn(Schedulers.io())
            .subscribe { db -> db.save(user.value!!) }

        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    fun getCurrentUser(): MediatorLiveData<LoggedInUser>{

       userFromDb = Single.just(userDao)
            .subscribeOn(Schedulers.io())
            .map { db -> db.getFirst() ?: throw NullPointerException() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({us -> user.value = us}, {user.value = null})

         return user
    }

    private fun refreshUser(userId: String) {
        // Check if user data was fetched recently.
       // val userExists = userDao.hasUser(userId, FRESH_TIMEOUT)
        if (!isLoggedIn) {
            // Refreshes the data.
            val response = dataSource.getUser(userId)

            // Check for errors here.

            // Updates the database. Since `userDao.load()` returns an object of
            // `Flow<User>`, a new `User` object is emitted every time there's a
            // change in the `User`  table.
            Single.just(userDao)
                .subscribeOn(Schedulers.io())
                .subscribe { db -> db.save(user.value!!) }

        }
    }
    companion object {
        val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }

}