package com.viktoriiaroi.weather.utils

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load

class BindingAdapters {
    companion object {
        private fun getIconURl(iconCode: String) =
            "https://openweathermap.org/img/wn/${iconCode}@2x.png"

        @BindingAdapter("android:loadImage")
        @JvmStatic
        fun loadImage(imageView: ImageView, iconCode: String?) {
            iconCode?.let {
                imageView.load(getIconURl(iconCode))
            }
        }

        @BindingAdapter("android:isVisible")
        @JvmStatic
        fun isVisible(view: View, isVisible: Boolean) {
            view.isVisible = isVisible
        }
    }
}