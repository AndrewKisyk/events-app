package com.evapps.data.remote

import com.evapps.data.local.Result
import com.evapps.data.models.LoggedInUser
import java.io.IOException
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor() {

    private fun provideFakeUser(email: String, name: String= "Thomas"): LoggedInUser {
        return LoggedInUser(java.util.UUID.randomUUID().toString(), email, name)
    }

    suspend fun login(email: String, password: String): Result<LoggedInUser> {
        return try {
            Result.Success(provideFakeUser(email))
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    suspend fun signup(userName: String, email: String, password: String): Result<LoggedInUser> {
        try {
            return Result.Success(provideFakeUser(email, userName))
        } catch (e: Throwable) {
            return Result.Error(IOException("Error Sign up", e))
        }
    }


    fun logout() {

    }
}