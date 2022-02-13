package com.imdmp.androidrapidprototype

import retrofit2.http.GET

interface SampleService {

    @GET(BuildConfig.GET_PLAYLIST)
    suspend fun getPlaylistData():List<VideoDataItem>
}