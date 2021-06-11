package com.example.submissionfundamental2.api

import com.example.submissionfundamental2.data.UserDetailResponse
import com.example.submissionfundamental2.data.UserResponse
import com.example.submissionfundamental2.data.UserArrayResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET("search/users")
    @Headers("Authorization: 11ac542bcaaaa0229265cad011c61e1b808c87a5")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserArrayResponse>


    @GET("users/{username}")
    @Headers("Authorization: 11ac542bcaaaa0229265cad011c61e1b808c87a5")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<UserDetailResponse>


    @GET("users/{username}")
    @Headers("Authorization: 11ac542bcaaaa0229265cad011c61e1b808c87a5")
    fun getUserMain(
        @Path("username") username: String
    ): Call<UserDetailResponse>


    @GET("users/{username}/followers")
    @Headers("Authorization: 11ac542bcaaaa0229265cad011c61e1b808c87a5")
    fun getUserFollowers(
        @Path("username") username: String
    ): Call<ArrayList<UserResponse>>


    @GET("users/{username}/following")
    @Headers("Authorization: 11ac542bcaaaa0229265cad011c61e1b808c87a5")
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<ArrayList<UserResponse>>
}