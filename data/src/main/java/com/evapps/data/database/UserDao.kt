package com.evapps.data.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.evapps.data.models.LoggedInUser

@Dao
interface UserDao {

    @Update
    suspend fun update(user: LoggedInUser?)

    @Insert(onConflict = REPLACE)
    suspend fun save(user: LoggedInUser)

    @Query("SELECT * FROM loggedInUser WHERE userId = :id")
    suspend fun load(id: String): LoggedInUser

    @Query("SELECT * FROM loggedInUser")
    suspend fun getFirst(): LoggedInUser?

    @Transaction
    suspend fun insertOrUpdate(loggedInUser: LoggedInUser) {
        val user = load(loggedInUser.userId)
        return if (user == null) {
            save(loggedInUser)
        } else {
            update(loggedInUser)
        }
    }
}