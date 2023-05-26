package com.storydicoding.jejevj.domain.usecase

import com.storydicoding.jejevj.domain.contract.GetStoryDetailUseCaseContract
import com.storydicoding.jejevj.domain.entity.StoryEntity
import com.storydicoding.jejevj.domain.interfaces.StoryRepository
import com.storydicoding.jejevj.domain.mapper.map
import com.storydicoding.jejevj.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetStoryDetailUseCase(private val storyRepository: StoryRepository) :
    GetStoryDetailUseCaseContract {
    override operator fun invoke(id: String): Flow<ResultState<StoryEntity>> = flow {
        emit(ResultState.Loading())
        storyRepository.getStory(id).map {
            it.story.map()
        }.catch {
            emit(ResultState.Error(message = it.message.toString()))
        }.collect {
            emit(ResultState.Success(it))
        }
    }
}
