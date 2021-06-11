package com.example.submissionfundamental2.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.submissionfundamental2.data.local.FavoriteUserDao
import com.example.submissionfundamental2.data.local.UserDatabase

class UserContentProvider : ContentProvider() {

    companion object {
        const val AUTH = "com.example.submissionfundamental2"
        const val TABLE_NAME = "favorite_user"
        const val ID_FAV_USER = 1
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTH, TABLE_NAME, ID_FAV_USER)
        }
    }

    private lateinit var userDao: FavoriteUserDao

    override fun onCreate(): Boolean {
        val let = context?.let {
            UserDatabase.getDatabase(it)?.favoriteUserDao()
        }
        userDao = if (let !=null) let else throw KotlinNullPointerException()
        return false
    }

    override fun query(
        uri: Uri, projection: Array<out String>?, selection: String?,
        selectionArgs: Array<out String>?, sortOrder: String?
    ): Cursor? {
        val cursor: Cursor?
        when (uriMatcher.match(uri)) {
            ID_FAV_USER -> {
                cursor = userDao.findAll()
                if (context != null) {
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> {
                cursor = null
            }
        }
        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int = 0

}