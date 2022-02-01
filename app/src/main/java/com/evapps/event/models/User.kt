package com.evapps.event.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val id:Int, val name: String, val status: String, val profileImage: String) :
    Parcelable {
}