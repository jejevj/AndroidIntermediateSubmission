package com.storydicoding.jejevj.ui.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.storydicoding.jejevj.domain.contract.GetStoriesUseCaseContract
import com.storydicoding.jejevj.domain.contract.GetUserUseCaseContract
import com.storydicoding.jejevj.domain.contract.LogoutUseCaseContract
import com.storydicoding.jejevj.domain.usecase.GetStoriesUseCase
import com.storydicoding.jejevj.domain.usecase.GetUserUseCase
import com.storydicoding.jejevj.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StoryViewModel(
    private val getStoriesUseCase: GetStoriesUseCaseContract,
    private val getUserUseCase: GetUserUseCaseContract,
    private val logoutUseCase: LogoutUseCaseContract
) : ViewModel() {
    private val _storyState = MutableStateFlow(StoryViewState())
    val storyState = _storyState.asStateFlow()

    fun getStories() {
        viewModelScope.launch {
            getStoriesUseCase().cachedIn(viewModelScope).collect { stories ->
                _storyState.update {
                    it.copy(resultStories = stories)
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }

    fun getUser() {
        viewModelScope.launch {
            getUserUseCase().collect { user ->
                _storyState.update {
                    it.copy(username = user.name)
                }
            }
        }
    }

    class Factory(
        private val getStoriesUseCase: GetStoriesUseCase,
        private val getUserUseCase: GetUserUseCase,
        private val logoutUseCase: LogoutUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StoryViewModel::class.java)) {
                return StoryViewModel(getStoriesUseCase, getUserUseCase, logoutUseCase) as T
            }
            error("Unknown ViewModel class: $modelClass")
        }
    }
}