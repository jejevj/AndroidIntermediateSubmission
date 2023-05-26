package com.storydicoding.jejevj.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.storydicoding.jejevj.Locator
import com.storydicoding.jejevj.databinding.ActivityStoryDetailBinding
import com.storydicoding.jejevj.domain.entity.StoryEntity
import com.storydicoding.jejevj.utils.ResultState
import com.storydicoding.jejevj.utils.launchAndCollectIn

class StoryDetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityStoryDetailBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<StoryDetailViewModel>(factoryProducer = { Locator.storyDetailViewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent.getStringExtra(EXTRA_STORY_ID)?.let {
            viewModel.getStoryDetail(it)
        }

        viewModel.storyDetailViewState.launchAndCollectIn(this) { state ->
            when (state.resultStory) {
                is ResultState.Success<StoryEntity> -> {
                    binding.progressBar.visibility = android.view.View.GONE
                    state.resultStory.data?.let {
                        binding.tvDetailName.text = it.name
                        binding.tvDetailDescription.text = it.description
                        Glide.with(this@StoryDetailActivity)
                            .load(it.photoUrl)
                            .into(binding.ivDetailPhoto)
                    }

                }
                is ResultState.Loading -> binding.progressBar.visibility = android.view.View.VISIBLE
                is ResultState.Error -> {
                    binding.progressBar.visibility = android.view.View.GONE
                    Toast.makeText(
                        this@StoryDetailActivity, state.resultStory.message, Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Unit
            }
        }
    }

    companion object {
        const val EXTRA_STORY_ID = "STORY_ID"
    }
}