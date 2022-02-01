package com.evapps.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.evapps.data.models.LoggedInUser
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Update
    fun update(user: LoggedInUser?)

    @Insert(onConflict = REPLACE)
    fun save(user: LoggedInUser)

    @Query("SELECT * FROM loggedInUser WHERE userId = :id")
    fun load(id: String): LoggedInUser

    @Query("SELECT * FROM loggedInUser")
    fun getFirst(): LoggedInUser?
}