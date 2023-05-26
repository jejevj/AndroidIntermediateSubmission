package com.storydicoding.jejevj.domain.contract

import com.google.android.gms.maps.model.LatLng
import com.storydicoding.jejevj.utils.ResultState
import kotlinx.coroutines.flow.Flow
import java.io.File

interface AddStoryUseCaseContract {
    operator fun invoke(file: File, description: String, latLng: LatLng?): Flow<ResultState<String>>
}