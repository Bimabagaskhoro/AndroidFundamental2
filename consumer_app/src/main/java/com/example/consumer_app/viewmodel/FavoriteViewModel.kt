package com.example.consumer_app.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.consumer_app.data.DatabaseContract
import com.example.consumer_app.data.MappingHelper
import com.example.consumer_app.data.UserResponse

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val list = MutableLiveData<ArrayList<UserResponse>>()

    fun setFavoriteUser(context: Context) {
        val cursor = context.contentResolver.query(
            DatabaseContract.FavoriteUserColums.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val listConverted = MappingHelper.mapCursorToArrayList(cursor)
        list.postValue(listConverted)
    }

    fun getFavoriteUser(): LiveData<ArrayList<UserResponse>> {
        return list
    }
}