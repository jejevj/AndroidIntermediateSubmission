package com.storydicoding.jejevj.ui.tambah

import com.storydicoding.jejevj.utils.ResultState

data class AddStoryViewState(
    val resultAddStory: ResultState<String> = ResultState.Idle()
)
