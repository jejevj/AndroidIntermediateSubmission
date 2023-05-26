package com.storydicoding.jejevj.domain.interfaces

import androidx.paging.PagingData
import com.google.android.gms.maps.model.LatLng
import com.storydicoding.jejevj.data.response.GeneralResponse
import com.storydicoding.jejevj.data.response.StoryDetailResponse
import com.storydicoding.jejevj.data.response.StoryListResponse
import com.storydicoding.jejevj.data.response.StoryResponse
import kotlinx.coroutines.flow.Flow
import java.io.File

interface StoryRepository {
    fun getStories(): Flow<PagingData<StoryResponse>>
    fun getStory(id: String): Flow<StoryDetailResponse>
    fun addStory(file: File, description: String, latLng: LatLng?): Flow<GeneralResponse>
    fun getStoriesLocation(id: Int): Flow<StoryListResponse>
}