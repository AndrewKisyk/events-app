package com.evapps.event.screens.main.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.navArgs

import com.evapps.event.R
import com.evapps.event.databinding.EventDetailsFragmentBinding
import com.evapps.event.databinding.EventsFragmentBinding
import com.evapps.event.models.Post
import com.evapps.event.screens.main.view_models.EventDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_row.*
import java.util.concurrent.TimeUnit

class EventDetailsFragment : Fragment() {

    private lateinit var postArgs: Post
    lateinit var binding: EventDetailsFragmentBinding
    companion object {
        fun newInstance() = EventDetailsFragment()
    }

    private lateinit var viewModel: EventDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = requireArguments()
        postArgs = EventDetailsFragmentArgs.fromBundle(args).model!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.event_details_fragment, container, false
        ) as EventDetailsFragmentBinding
        val view = binding.root
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EventDetailsViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = postArgs
    }

}