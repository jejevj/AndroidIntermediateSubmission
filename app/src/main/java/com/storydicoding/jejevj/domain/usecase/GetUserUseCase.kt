package com.storydicoding.jejevj.domain.usecase

import com.storydicoding.jejevj.domain.contract.GetUserUseCaseContract
import com.storydicoding.jejevj.domain.entity.UserEntity
import com.storydicoding.jejevj.domain.interfaces.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val userPreferenceRepository: UserPreferenceRepository) :
    GetUserUseCaseContract {
    override fun invoke(): Flow<UserEntity> = userPreferenceRepository.userData
}