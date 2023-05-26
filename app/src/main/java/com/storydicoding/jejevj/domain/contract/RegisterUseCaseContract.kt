package com.storydicoding.jejevj.domain.contract

import com.storydicoding.jejevj.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface RegisterUseCaseContract {
    operator fun invoke(name: String, email: String, password: String): Flow<ResultState<String>>
}