package com.evapps.event.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.evapps.event.BR
import com.evapps.event.R
import com.evapps.event.extensions.NavigationExtensions.navigateSafe
import com.evapps.event.models.Event
import com.evapps.event.models.FakeEvents
import com.evapps.event.models.FakePosts
import com.evapps.event.models.Post
import com.evapps.event.screens.main.view_models.EventsViewModel
import com.evapps.event.screens.main.view_models.ViewModelAdapter
import com.evapps.event.screens.main.views.EventsFragmentDirections
import de.hdodenhof.circleimageview.CircleImageView

object EventsAdapter : ViewModelAdapter() {
    init {
        cell(Post::class.java, R.layout.event_row, BR.vm)
        sharedObject(this, BR.adapter)
        items = FakePosts.events as Array<Any>

        notifyDataSetChanged()
    }

    fun itemSelected(view: View, model: Post) {
        val background = view.findViewById(R.id.constraintLayout) as ConstraintLayout

        val extras = FragmentNavigatorExtras(
            background to model.event.id.toString()
        )

        val action =
            EventsFragmentDirections.actionEventsFragmentToEventsDetails(model)
        findNavController(view.findFragment()).navigateSafe(action, extras)
    }
}
