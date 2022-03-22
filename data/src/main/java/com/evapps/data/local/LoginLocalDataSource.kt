package com.evapps.data.local

import com.evapps.data.database.UserDao
import com.evapps.data.models.LoggedInUser
import java.io.IOException
import javax.inject.Inject

class LoginLocalDataSource @Inject constructor(val userDao: UserDao) {

    suspend fun login(loggedInUser: LoggedInUser): Result<LoggedInUser>{
        return try {
            userDao.insertOrUpdate(loggedInUser)
            Result.Success(loggedInUser)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    suspend fun getUser(userId: String): Result<LoggedInUser> {
        return try {
            val user = userDao.load(userId) ?: throw NoSuchElementException()
            Result.Success(user)
        } catch (e: Throwable) {
            Result.Error(IOException("Error getting user", e))
        }
    }

    suspend fun signup(loggedInUser: LoggedInUser): Result<LoggedInUser>{
        try {
             userDao.save(loggedInUser)
            return Result.Success(loggedInUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error Sign up", e))
        }
    }


    fun logout() {

    }

}