package com.storydicoding.jejevj.domain.contract

import com.storydicoding.jejevj.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface GetUserUseCaseContract {
    operator fun invoke(): Flow<UserEntity>
}