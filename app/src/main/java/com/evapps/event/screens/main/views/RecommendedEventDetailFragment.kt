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
import com.evapps.event.R
import com.evapps.event.databinding.EventDetailsFragmentBinding
import com.evapps.event.databinding.RecommendedEventDetailFragmentBinding
import com.evapps.event.models.Event
import com.evapps.event.models.Post
import com.evapps.event.screens.main.view_models.RecommendedEventDetailViewModel
import java.util.concurrent.TimeUnit

class RecommendedEventDetailFragment : Fragment() {
    companion object {
        fun newInstance() = RecommendedEventDetailFragment()
    }
    private lateinit var eventArgs: Event

    private lateinit var viewModel: RecommendedEventDetailViewModel
    lateinit var binding: RecommendedEventDetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = requireArguments()
        eventArgs = RecommendedEventDetailFragmentArgs.fromBundle(args).model!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.recommended_event_detail_fragment, container, false
        ) as RecommendedEventDetailFragmentBinding
        val view = binding.root
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecommendedEventDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = eventArgs
    }
}