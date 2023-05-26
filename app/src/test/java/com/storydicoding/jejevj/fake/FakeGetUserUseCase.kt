package com.storydicoding.jejevj.fake

import com.storydicoding.jejevj.domain.contract.GetUserUseCaseContract
import com.storydicoding.jejevj.domain.entity.UserEntity
import com.storydicoding.jejevj.utils.FakeFlowDelegate
import kotlinx.coroutines.flow.Flow

class FakeGetUserUseCase : GetUserUseCaseContract {

    val fakeDelegate = FakeFlowDelegate<UserEntity>()

    override fun invoke(): Flow<UserEntity> = fakeDelegate.flow

}