package com.storydicoding.jejevj.ui.maps

import com.storydicoding.jejevj.domain.entity.StoryEntity
import com.storydicoding.jejevj.utils.ResultState

data class MapsViewState(
    val resultStories: ResultState<List<StoryEntity>> = ResultState.Idle(),
)