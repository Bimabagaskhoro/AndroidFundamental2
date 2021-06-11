package com.example.consumer_app.data

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTH = "com.example.submissionfundamental2"
    const val SCHME = "content"

    internal class FavoriteUserColums : BaseColumns {
        companion object {
            private const val TABLE_NAME = "favorite_user"
            const val ID = "id"
            const val USERNAME = "login"
            const val AVATAR_URL = "avatar_url"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHME)
                .authority(AUTH)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}