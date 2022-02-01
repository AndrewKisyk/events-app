package com.evapps.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evapps.data.models.LoggedInUser

@Database(entities = [LoggedInUser::class], version = 1)
abstract class UserDatabase:RoomDatabase(){
    abstract fun userDao(): UserDao
}