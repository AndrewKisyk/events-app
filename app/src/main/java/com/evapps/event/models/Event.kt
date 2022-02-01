package com.evapps.event.models

import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.evapps.event.R
import com.squareup.picasso.Picasso
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val id: Int,
    val title: String,
    val image: String,
    val going: Int,
    val owner: User,
    val location: String,
    val date: String,
    val description: String
) : Parcelable