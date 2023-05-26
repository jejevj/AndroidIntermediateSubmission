package com.storydicoding.jejevj.domain.contract

import androidx.paging.PagingData
import com.storydicoding.jejevj.domain.entity.StoryEntity
import kotlinx.coroutines.flow.Flow

interface GetStoriesUseCaseContract {
    operator fun invoke(): Flow<PagingData<StoryEntity>>
}