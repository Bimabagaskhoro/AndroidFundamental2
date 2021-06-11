package com.example.consumer_app.data

import android.database.Cursor

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<UserResponse> {
        val list = ArrayList<UserResponse>()

        cursor?.apply {
            while (moveToNext()) {
                val username =
                    cursor.getString(getColumnIndexOrThrow(DatabaseContract.FavoriteUserColums.USERNAME))
                val id =
                    cursor.getInt(getColumnIndexOrThrow(DatabaseContract.FavoriteUserColums.ID))
                val avatar_url =
                    cursor.getString(getColumnIndexOrThrow(DatabaseContract.FavoriteUserColums.AVATAR_URL))
                list.add(
                    UserResponse(
                        username,
                        id,
                        avatar_url
                    )
                )
            }
        }
        return list
    }
}