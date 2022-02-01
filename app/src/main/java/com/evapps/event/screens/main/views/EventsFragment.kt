package com.evapps.event.screens.main.views

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.evapps.event.EventApp
import com.evapps.event.R
import com.evapps.event.adapters.EventsAdapter
import com.evapps.event.adapters.RecommendedEventsAdapter
import com.evapps.event.databinding.EventsFragmentBinding
import com.evapps.event.screens.main.view_models.ProfileViewModel
import kotlinx.android.synthetic.main.events_fragment.*
import uz.jamshid.library.progress_bar.CircleProgressBar
import javax.inject.Inject

class EventsFragment : Fragment() {

    @Inject
    lateinit var viewModel: ProfileViewModel

    lateinit var binding: EventsFragmentBinding
    var onTop: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        EventApp.appComponent.inject(fragment = this)

        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.events_fragment, container, false
        ) as EventsFragmentBinding

        val view = binding.root
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        initSwipe(view.context)
        initSwipeListener()
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recommendedAdapter = RecommendedEventsAdapter
        binding.eventsAdapter = EventsAdapter
        // When user hits back button transition takes backward
        postponeEnterTransition()
        binding.events.doOnPreDraw {
            startPostponedEnterTransition()
        }

        postponeEnterTransition()
        binding.recomendations.doOnPreDraw {
            startPostponedEnterTransition()
        }

    }

    fun initSwipe(context: Context) {
        binding.swipe.setRefreshListener {
            Handler().postDelayed({
                swipe.setRefreshing(false)
            }, 1000)
        }

        val cp = CircleProgressBar(context)
        cp.setColors(
            resources.getColor(R.color.colorPrimaryDark),
            resources.getColor(R.color.colorPrimary)
        )
        cp.setBorderWidth(2)

        binding.swipe.setCustomBar(cp)
    }

    fun initSwipeListener() {
        binding.nestedScroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            binding.swipe.isEnabled = scrollY == 0

        })
    }


}