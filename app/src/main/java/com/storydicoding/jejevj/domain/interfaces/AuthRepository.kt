package com.storydicoding.jejevj.domain.interfaces

import com.storydicoding.jejevj.data.response.GeneralResponse
import com.storydicoding.jejevj.data.response.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun register(email: String, password: String, name: String): Flow<GeneralResponse>
    fun login(email: String, password: String): Flow<LoginResponse>
}