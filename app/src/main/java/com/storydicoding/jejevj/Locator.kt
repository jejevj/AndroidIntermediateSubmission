package com.storydicoding.jejevj

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.storydicoding.jejevj.data.repository.AuthRepositoryImpl
import com.storydicoding.jejevj.data.repository.StoryRepositoryImpl
import com.storydicoding.jejevj.data.source.database.StoryDatabase
import com.storydicoding.jejevj.data.source.local.UserPreferenceImpl
import com.storydicoding.jejevj.data.source.remote.RetrofitBuilder
import com.storydicoding.jejevj.domain.usecase.AddStoryUseCase
import com.storydicoding.jejevj.domain.usecase.GetStoriesLocationUseCase
import com.storydicoding.jejevj.domain.usecase.GetStoriesUseCase
import com.storydicoding.jejevj.domain.usecase.GetStoryDetailUseCase
import com.storydicoding.jejevj.domain.usecase.GetUserUseCase
import com.storydicoding.jejevj.domain.usecase.LoginUseCase
import com.storydicoding.jejevj.domain.usecase.LogoutUseCase
import com.storydicoding.jejevj.domain.usecase.RegisterUseCase
import com.storydicoding.jejevj.ui.tambah.AddStoryViewModel
import com.storydicoding.jejevj.ui.detail.StoryDetailViewModel
import com.storydicoding.jejevj.ui.login.LoginViewModel
import com.storydicoding.jejevj.ui.maps.MapsViewModel
import com.storydicoding.jejevj.ui.daftar.RegisterViewModel
import com.storydicoding.jejevj.ui.story.StoryViewModel
import com.storydicoding.jejevj.ui.welcome.WelcomeViewModel

object Locator {
    private var application: Application? = null

    private inline val requireApplication
        get() = application ?: error("Missing call: initWith(application)")

    fun initWith(application: Application) {
        this.application = application
    }

    // Data Store
    private val Context.dataStore by preferencesDataStore(name = "user_preferences")

    // ViewModel Factory
    val loginViewModelFactory
        get() = LoginViewModel.Factory(
            loginUseCase = loginUseCase
        )
    val registerViewModelFactory
        get() = RegisterViewModel.Factory(
            registerUseCase = registerUseCase
        )
    val welcomeViewModelFactory
        get() = WelcomeViewModel.Factory(
            getUserUseCase = getUserUseCase
        )
    val storyViewModelFactory
        get() = StoryViewModel.Factory(
            getStoriesUseCase = getStoriesUseCase,
            getUserUseCase = getUserUseCase,
            logoutUseCase = logoutUseCase
        )
    val storyDetailViewModelFactory
        get() = StoryDetailViewModel.Factory(
            getStoryDetailUseCase = getStoryDetailUseCase
        )
    val addStoryViewModelFactory
        get() = AddStoryViewModel.Factory(
            addStoryUseCase = addStoryUseCase
        )
    val mapsViewModelFactory
        get() = MapsViewModel.Factory(
            getStoriesLocationUseCase = getStoriesLocationUseCase
        )

    // UseCases Injection
    private val loginUseCase get() = LoginUseCase(userPreferencesRepository, authRepository)
    private val registerUseCase get() = RegisterUseCase(authRepository)
    private val getUserUseCase get() = GetUserUseCase(userPreferencesRepository)
    private val getStoriesUseCase get() = GetStoriesUseCase(storyRepository)
    private val logoutUseCase get() = LogoutUseCase(userPreferencesRepository)
    private val getStoryDetailUseCase get() = GetStoryDetailUseCase(storyRepository)
    private val addStoryUseCase get() = AddStoryUseCase(storyRepository)
    private val getStoriesLocationUseCase get() = GetStoriesLocationUseCase(storyRepository)

    // Repository Injection
    private val userPreferencesRepository by lazy {
        UserPreferenceImpl(requireApplication.dataStore)
    }
    private val authRepository by lazy {
        AuthRepositoryImpl(RetrofitBuilder(requireApplication.dataStore).apiService)
    }
    private val storyRepository by lazy {
        StoryRepositoryImpl(
            StoryDatabase.getDatabase(requireApplication),
            RetrofitBuilder(requireApplication.dataStore).apiService
        )
    }
}