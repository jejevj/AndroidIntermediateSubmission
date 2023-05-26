package com.storydicoding.jejevj.data.response


data class StoryListResponse(
    val error: Boolean,
    val message: String,
    val listStory: List<StoryResponse>
)