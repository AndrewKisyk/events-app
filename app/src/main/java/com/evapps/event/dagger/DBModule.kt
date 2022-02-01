package com.evapps.event.dagger

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evapps.data.database.UserDao
import com.evapps.data.database.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "UserDatabase")
            .build()
    }


    @Singleton
    @Provides
    fun provideDao(database: UserDatabase): UserDao {
        return database.userDao()
    }
}