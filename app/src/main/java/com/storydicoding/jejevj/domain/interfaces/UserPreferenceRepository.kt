package com.storydicoding.jejevj.domain.interfaces

import com.storydicoding.jejevj.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {
    val userData: Flow<UserEntity>
    suspend fun saveUser(userEntity: UserEntity)
    suspend fun clearUser()
}