package com.evapps.event.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(val id: Int, var postOwner: User, val event: Event, val timePublished: String) :
    Parcelable