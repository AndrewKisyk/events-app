package com.evapps.data.local

import com.evapps.data.models.LoggedInUser
import java.io.IOException
import javax.inject.Inject

class LoginDataSource @Inject constructor(){

    fun login(email: String, password:String): Result<LoggedInUser>{
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), email, "Thomas")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun getUser(userId: String): Result<LoggedInUser> {
        val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "thomas@gmail.com", "Thom")
        return Result.Success(fakeUser)
    }

    fun signup(username: String, email: String, password: String): Result<LoggedInUser>{
        try {
            // TODO: handle signup authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), email, username)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error Sign up", e))
        }
    }


    fun logout() {

    }

}