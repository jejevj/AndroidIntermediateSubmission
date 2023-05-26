package com.storydicoding.jejevj.ui.welcome

import com.storydicoding.jejevj.utils.ResultState

data class WelcomeViewState(
    val resultIsLoggedIn: ResultState<Boolean> = ResultState.Idle()
)
