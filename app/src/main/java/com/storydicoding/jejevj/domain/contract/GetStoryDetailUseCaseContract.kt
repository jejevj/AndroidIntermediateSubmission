package com.storydicoding.jejevj.domain.contract

import com.storydicoding.jejevj.domain.entity.StoryEntity
import com.storydicoding.jejevj.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface GetStoryDetailUseCaseContract {
    operator fun invoke(id: String): Flow<ResultState<StoryEntity>>
}