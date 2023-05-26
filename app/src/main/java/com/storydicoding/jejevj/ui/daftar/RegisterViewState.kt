package com.storydicoding.jejevj.ui.daftar

import com.storydicoding.jejevj.utils.ResultState

data class RegisterViewState(
    val resultRegisterUser: ResultState<String> = ResultState.Idle()
)