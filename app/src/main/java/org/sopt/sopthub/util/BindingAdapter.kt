package org.sopt.sopthub.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("circleImgUrl")
    fun setRemoteCircleImage(image: ImageView, url: String?) {
        Glide.with(image.context).load(url).circleCrop().into(image)
    }
}