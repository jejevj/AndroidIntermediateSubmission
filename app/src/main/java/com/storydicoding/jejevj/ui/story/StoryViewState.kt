package com.storydicoding.jejevj.ui.story

import androidx.paging.PagingData
import com.storydicoding.jejevj.domain.entity.StoryEntity

data class StoryViewState(
    val resultStories: PagingData<StoryEntity> = PagingData.empty(),
    val username: String = "",
)