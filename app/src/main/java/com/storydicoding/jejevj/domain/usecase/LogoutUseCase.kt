package com.storydicoding.jejevj.domain.usecase

import com.storydicoding.jejevj.domain.contract.LogoutUseCaseContract
import com.storydicoding.jejevj.domain.interfaces.UserPreferenceRepository

class LogoutUseCase(private val userPreferenceRepository: UserPreferenceRepository) :
    LogoutUseCaseContract {
    override suspend fun invoke() = userPreferenceRepository.clearUser()
}