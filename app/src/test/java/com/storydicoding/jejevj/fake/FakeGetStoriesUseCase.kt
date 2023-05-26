package com.storydicoding.jejevj.fake

import androidx.paging.PagingData
import com.storydicoding.jejevj.domain.contract.GetStoriesUseCaseContract
import com.storydicoding.jejevj.domain.entity.StoryEntity
import com.storydicoding.jejevj.utils.FakeFlowDelegate
import kotlinx.coroutines.flow.Flow

class FakeGetStoriesUseCase : GetStoriesUseCaseContract {

    val fakeDelegate = FakeFlowDelegate<PagingData<StoryEntity>>()

    override fun invoke(): Flow<PagingData<StoryEntity>> = fakeDelegate.flow

}