package com.storydicoding.jejevj.domain.contract

import com.storydicoding.jejevj.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface LoginUseCaseContract {
    operator fun invoke(email: String, password: String): Flow<ResultState<String>>
}