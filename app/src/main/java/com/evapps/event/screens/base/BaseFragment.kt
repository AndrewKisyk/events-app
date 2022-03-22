package com.evapps.event.screens.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.evapps.event.R
import com.evapps.event.extensions.lifecycleAwareLazy
import com.evapps.event.extensions.retrieveSharedViewModel
import com.evapps.event.extensions.retrieveViewModel
import com.evapps.event.models.LoggedInUserView
import com.evapps.event.other.SharedViewModelFactory
import kotlin.system.exitProcess


abstract class BaseFragment<VS : BaseViewState, VM : BaseViewModel<VS>> : Fragment(),
    LifecycleOwner {

    var viewModelFactory: ViewModelProvider.Factory = SharedViewModelFactory()

    protected abstract val viewModel: VM

    protected inline fun <reified VM : ViewModel> lazyViewModel(): Lazy<VM> =
        lifecycleAwareLazy(this) {
            retrieveViewModel<VM>(viewModelFactory)
        }

    protected inline fun <reified VM : ViewModel> lazySharedViewModel(): Lazy<VM> =
        lifecycleAwareLazy(this) {
            retrieveSharedViewModel<VM>(viewModelFactory)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeState(::onStateChange)
        observeNavigationEvent()
    }

    private fun observeState(callback: (state: VS) -> Unit) {
        val observer = Observer<VS> { state -> callback.invoke(state) }
        viewModel.viewState.observe(viewLifecycleOwner, observer)
        observer.onChanged(viewModel.state)     // Deliver initial state because initial state was initialized when there wasn't an observer observing state live data.
    }

    /** Implement this in subclasses to listen to state changes */
    protected abstract fun onStateChange(state: VS)

    private fun observeNavigationEvent() {
        viewModel.navigationEvent.observe(viewLifecycleOwner, Observer {
            val consume = it?.consume()
            consume?.invoke(findNavController())
        })
    }

    @SuppressLint("RestrictedApi")
    protected fun onBackPressed() {
        viewModel.onBackPressed()
        if(findNavController().backStack.size > 0) {
            findNavController().popBackStack()
            Log.d("GO", "Back! "+ findNavController().backStack.size)
        } else {
            Log.d("GO", "Back")
            activity?.finish();
            exitProcess(0);
        }

    }

    protected open fun onReturnToPreviousScreen() {
        findNavController().popBackStack()
    }

    fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        Toast.makeText(
            context,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    fun showToastError(@StringRes errorString: Int) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}