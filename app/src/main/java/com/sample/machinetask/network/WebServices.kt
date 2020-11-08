package com.sample.machinetask.network;

import com.sample.machinetask.model.APIResponse

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface WebServices {



    @GET("everything?q=bitcoin")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") limit: Int
    ): APIResponse


}