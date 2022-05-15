package com.example.movieapplatest.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourcesUtil(var context: Context) {

    val layoutInflater: LayoutInflater get() = LayoutInflater.from(context)

    fun getString(@StringRes resourceId: Int): String {
        return context.getString(resourceId)
    }

    fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }

    fun getDrawable(@DrawableRes resourceId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resourceId)
    }

}