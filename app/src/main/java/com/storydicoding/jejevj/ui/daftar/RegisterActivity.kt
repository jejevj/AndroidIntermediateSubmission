package com.storydicoding.jejevj.ui.daftar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.storydicoding.jejevj.Locator
import com.storydicoding.jejevj.R
import com.storydicoding.jejevj.databinding.ActivityRegisterBinding
import com.storydicoding.jejevj.ui.login.LoginActivity
import com.storydicoding.jejevj.utils.ResultState
import com.storydicoding.jejevj.utils.launchAndCollectIn

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<RegisterViewModel>(factoryProducer = { Locator.registerViewModelFactory })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.registerViewState.launchAndCollectIn(this) {
            when (it.resultRegisterUser) {
                is ResultState.Success<String> -> {
                    binding.btRegister.setLoading(false)
                    Toast.makeText(
                        this@RegisterActivity,
                        getString(R.string.register_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(
                        Intent(
                            this@RegisterActivity, LoginActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    finish()
                }
                is ResultState.Loading -> binding.btRegister.setLoading(true)
                is ResultState.Error -> {
                    binding.btRegister.setLoading(false)
                    Toast.makeText(
                        this@RegisterActivity, it.resultRegisterUser.message, Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Unit
            }
        }
        binding.btRegister.setOnClickListener {
            viewModel.registerUser(
                name = binding.edRegisterName.text.toString(),
                email = binding.edRegisterEmail.text.toString(),
                password = binding.edRegisterPassword.text.toString()
            )
        }
        binding.tvAlreadyHaveAnAccount.setOnClickListener {
            startActivity(
                Intent(
                    this, LoginActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        }
    }
}