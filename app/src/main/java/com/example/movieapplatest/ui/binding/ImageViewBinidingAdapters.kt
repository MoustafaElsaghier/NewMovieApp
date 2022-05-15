package com.example.movieapplatest.ui.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.movieapplatest.BuildConfig
import com.example.movieapplatest.R
import timber.log.Timber

@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?) {
    val ph = ContextCompat.getDrawable(imageView.context, R.mipmap.ic_launcher)
    if (url == null) {
        imageView.setImageDrawable(ph)
    } else {

        val fullUrl =  BuildConfig.BASE_Image_URL + url
        Timber.tag("setImageUrl=>>").d(fullUrl)
        Glide.with(imageView)
            .load(fullUrl)
            .placeholder(ph)
            .error(ph)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            }).into(imageView)
    }
}