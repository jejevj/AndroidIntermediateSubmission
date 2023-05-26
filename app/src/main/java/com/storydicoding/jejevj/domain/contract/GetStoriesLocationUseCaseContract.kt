package com.storydicoding.jejevj.domain.contract

import com.storydicoding.jejevj.domain.entity.StoryEntity
import com.storydicoding.jejevj.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface GetStoriesLocationUseCaseContract {
    operator fun invoke(): Flow<ResultState<List<StoryEntity>>>
}