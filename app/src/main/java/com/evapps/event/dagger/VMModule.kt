package com.evapps.event.dagger

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.evapps.domain.repositories.LoginRepository
import com.evapps.domain.usecases.FetchMusicEventsUseCase
import com.evapps.event.screens.log_in.view_models.ForgetPasswordViewModel
import com.evapps.event.screens.log_in.view_models.LogInViewModel
import com.evapps.event.screens.log_in.view_models.SignUpViewModel
import com.evapps.event.screens.log_in.view_models.UserViewModel
import com.evapps.event.screens.main.MainViewModel
import com.evapps.event.screens.main.view_models.EventsViewModel
import com.evapps.event.screens.main.view_models.ProfileViewModel
import com.evapps.event.screens.splash.SplashViewModel
import dagger.Module
import dagger.Provides

@Module
class VMModule {

    @Provides
    fun provideViewModelFactory(application: Application): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory(application)
    }
    @Provides
    fun provideSplashViewModel(loginRepository: LoginRepository): SplashViewModel {
        return SplashViewModel()
    }

    @Provides
    fun provideMainViewModel(factory: ViewModelProvider.Factory, fetchMusicEventsUseCase: FetchMusicEventsUseCase):MainViewModel {
        val viewModel = factory.create(MainViewModel::class.java)
        viewModel.fetchMusicEventsUseCase = fetchMusicEventsUseCase
        return viewModel
    }

    @Provides
    fun provideLogInViewModel(loginRepository: LoginRepository): LogInViewModel {
        return LogInViewModel(
            loginRepository
        )
    }

    @Provides
    fun provideSignUpViewModel(loginRepository: LoginRepository): SignUpViewModel {
        return SignUpViewModel(
            loginRepository
        )
    }

    @Provides
    fun provideForgetPasswordViewModel(loginRepository: LoginRepository): ForgetPasswordViewModel {
        return ForgetPasswordViewModel(
            loginRepository
        )
    }

    @Provides
    fun provideUserViewModel(loginRepository: LoginRepository): UserViewModel {
        return UserViewModel(loginRepository)
    }

    @Provides
    fun provideProfileViewModel(loginRepository: LoginRepository): ProfileViewModel {
        return ProfileViewModel(loginRepository)
    }

    @Provides
    fun provideEventsViewModel(): EventsViewModel {
        return EventsViewModel()
    }

}