package com.evapps.event.adapters

import android.graphics.drawable.Drawable
import android.icu.number.NumberRangeFormatter.with

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import com.evapps.event.BR
import com.evapps.event.R
import com.evapps.event.models.Event
import com.evapps.event.models.FakeEvents
import com.evapps.event.models.FakePosts
import com.evapps.event.models.Post
import com.evapps.event.screens.main.view_models.EventsViewModel
import com.evapps.event.screens.main.view_models.ViewModelAdapter
import com.evapps.event.screens.main.views.EventsFragmentDirections
import com.squareup.picasso.Picasso


object  RecommendedEventsAdapter : ViewModelAdapter() {
    init {

        cell(Event::class.java, R.layout.recomended_events, BR.vm)


        sharedObject(this, BR.adapter)

        items = FakeEvents.events as Array<Any>

        Log.d("Adapter", "Its ok")
        notifyDataSetChanged()

    }


    object DataBindingAdapter {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageViewResource(imageView: ImageView, url: String) {
            Picasso.get().load(url).into(imageView)
        }
    }



    fun itemSelected(view: View, model: Event) {

        val background = view.findViewById(R.id.constraintLayout2) as ConstraintLayout
        val extras = FragmentNavigatorExtras(
            background to model.title
        )
        val action = EventsFragmentDirections.actionEventsFragmentToRecommendedDetails(model)
        NavHostFragment.findNavController(view.findFragment()).navigate(action, extras)
    }

}