package com.storydicoding.jejevj.ui.story

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.storydicoding.jejevj.Locator
import com.storydicoding.jejevj.databinding.ActivityStoryBinding
import com.storydicoding.jejevj.ui.adapter.LoadingStateAdapter
import com.storydicoding.jejevj.ui.adapter.StoryAdapter
import com.storydicoding.jejevj.ui.tambah.AddStoryActivity
import com.storydicoding.jejevj.ui.login.LoginActivity
import com.storydicoding.jejevj.ui.maps.MapsActivity
import com.storydicoding.jejevj.utils.launchAndCollectIn

class StoryActivity : AppCompatActivity() {
    private val binding by lazy { ActivityStoryBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<StoryViewModel>(factoryProducer = { Locator.storyViewModelFactory })
    private val adapter by lazy { StoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initAdapter()
        viewModel.storyState.launchAndCollectIn(this) {
            adapter.submitData(lifecycle, it.resultStories)
            binding.tvName.text = it.username
        }

        binding.fabAddStory.setOnClickListener {
            startActivity(Intent(this@StoryActivity, AddStoryActivity::class.java))
        }
        binding.actionLogout.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this@StoryActivity, LoginActivity::class.java))
            finish()
        }

        binding.actionChangeLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
        binding.actionMaps.setOnClickListener {
            startActivity(Intent(this@StoryActivity, MapsActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getStories()
        viewModel.getUser()
    }

    private fun initAdapter() {
        binding.rvStory.adapter = adapter.withLoadStateFooter(footer = LoadingStateAdapter {
            adapter.retry()
        })
        binding.rvStory.layoutManager = LinearLayoutManager(this)
    }
}