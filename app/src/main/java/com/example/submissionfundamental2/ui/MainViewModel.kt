package com.example.submissionfundamental2.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submissionfundamental2.api.RetrofitClient
import com.example.submissionfundamental2.data.UserResponse
import com.example.submissionfundamental2.data.UserArrayResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listUser = MutableLiveData<ArrayList<UserResponse>>()

    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserArrayResponse> {
                override fun onResponse(
                    call: Call<UserArrayResponse>,
                    arrayResponse: Response<UserArrayResponse>
                ) {
                    if (arrayResponse.isSuccessful) {
                        listUser.postValue(arrayResponse.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserArrayResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getSearchUsers(): LiveData<ArrayList<UserResponse>> {
        return listUser
    }
}