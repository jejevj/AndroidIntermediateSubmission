package com.storydicoding.jejevj.ui.detail

import com.storydicoding.jejevj.domain.entity.StoryEntity
import com.storydicoding.jejevj.utils.ResultState

data class StoryDetailViewState(
    val resultStory: ResultState<StoryEntity> = ResultState.Idle()
)