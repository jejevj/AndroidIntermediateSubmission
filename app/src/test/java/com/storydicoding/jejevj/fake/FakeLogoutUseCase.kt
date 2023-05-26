package com.storydicoding.jejevj.fake

import com.storydicoding.jejevj.domain.contract.LogoutUseCaseContract

class FakeLogoutUseCase : LogoutUseCaseContract {


    override suspend fun invoke() = Unit


}