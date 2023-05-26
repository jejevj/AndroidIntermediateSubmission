package com.storydicoding.jejevj.domain.usecase

import com.storydicoding.jejevj.domain.contract.GetStoriesUseCaseContract
import com.storydicoding.jejevj.domain.interfaces.StoryRepository
import com.storydicoding.jejevj.domain.mapper.map
import kotlinx.coroutines.flow.map

class GetStoriesUseCase(private val storyRepository: StoryRepository) : GetStoriesUseCaseContract {
    override fun invoke() = storyRepository.getStories().map { pagingData ->
        pagingData.map()
    }

}