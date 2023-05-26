package com.storydicoding.jejevj.ui.login

import com.storydicoding.jejevj.utils.ResultState

data class LoginViewState(
    val resultVerifyUser: ResultState<String> = ResultState.Idle()
)
