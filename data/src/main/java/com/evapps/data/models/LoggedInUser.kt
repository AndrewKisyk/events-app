package com.evapps.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggedInUser(
    @PrimaryKey val userId: String,
     val email: String,
     val displayName: String
)

