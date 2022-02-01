package com.evapps.event.screens.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.navigation.Navigation
import com.evapps.event.EventApp
import com.evapps.event.R
import com.evapps.event.models.Language
import com.evapps.event.screens.base.BaseFragment
import com.evapps.event.screens.log_in.AuthActivity
import com.evapps.event.utils.PreferenceManager
import kotlinx.android.synthetic.main.splash_fragment.*
import javax.inject.Inject


class SplashFragment : BaseFragment<SplashState, SplashViewModel>() {

    @Inject override lateinit var viewModel: SplashViewModel


    @Inject
    lateinit var preferenceManager: PreferenceManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        EventApp.appComponent.inject(fragment = this)
        /*viewModel.userIsLoggedIn()
        activity?.finish()*/

        val view = inflater.inflate(R.layout.splash_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onCredentialsSubmitted()

     //   viewModel.setUpLanguagesList(preferenceManager, Language.languages.get(0))
        languageSpinner.adapter = activity?.let {
            LanguageAdapter(
                it
            )
        }
        initListeners()
    }


    override fun onStateChange(state: SplashState) {
        start.isEnabled = state.splashContinue
    }

    private fun initListeners() {
        start.setOnClickListener { viewModel.onContinueClick() }
        context?.let { initSpinnerListener(it) }
    }

    private fun initSpinnerListener(context: Context) {

        var isSpinnerTouched = false
        languageSpinner.setOnTouchListener(OnTouchListener { v, event ->
            isSpinnerTouched = true
            false
        })

        languageSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(!isSpinnerTouched) return
                   viewModel.toggleLanguages(
                        preferenceManager,
                        language = parent.getItemAtPosition(position) as Language
                    )
                startActivity(Intent(activity, AuthActivity::class.java))
                activity?.finish()
                Log.d("Spinner Click", "Clicked")
            } // to close the onItemSelected
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }



}