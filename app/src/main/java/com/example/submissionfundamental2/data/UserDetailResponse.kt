package com.example.submissionfundamental2.data

data class UserDetailResponse(
        val login: String,
        val id: Int,
        val avatar_url: String,
        val followers_url: String,
        val following_url: String,
        val name: String,
        val company: String,
        val bio: String,
        val location: String,
        val following: Int,
        val followers: Int
)
