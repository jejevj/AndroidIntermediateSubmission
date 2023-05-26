package com.storydicoding.jejevj.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.storydicoding.jejevj.domain.contract.LoginUseCaseContract
import com.storydicoding.jejevj.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.*

class LoginViewModel(
    private val loginUseCase: LoginUseCaseContract
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginViewState())
    val loginState = _loginState.asStateFlow()


    fun doLogin(email: String, password: String) {
        if(password.length<=7){
//            Toast.makeText(this,"Password Anda Masih Kurang dari 8", Toast.LENGTH_SHORT).show()
        }else {
            loginUseCase(email, password)
                .onEach { result ->
                    _loginState.update {
                        it.copy(resultVerifyUser = result)
                    }
                }.launchIn(viewModelScope)
        }
    }
    class Factory(
        private val loginUseCase: LoginUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(loginUseCase) as T
            }
            error("Unknown ViewModel class: $modelClass")
        }
    }

}

