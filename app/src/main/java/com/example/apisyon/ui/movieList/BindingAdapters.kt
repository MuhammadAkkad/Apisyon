package com.example.apisyon.ui.movieList

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.apisyon.R
import com.example.apisyon.ui.model.MovieGrade

class BindingAdapters {

    companion object {

        @BindingAdapter("android:rankTint")
        @JvmStatic
        fun setRankColor(view: View, color: MovieGrade) {
            try {
                when (color) {
                    MovieGrade.LOW -> {
                        view.backgroundTintList =
                            ColorStateList.valueOf(view.context.getColor(R.color.red))
                    }

                    MovieGrade.MEDIOCRE -> {
                        view.backgroundTintList =
                            ColorStateList.valueOf(view.context.getColor(R.color.orange))
                    }

                    MovieGrade.HIGH -> {
                        view.backgroundTintList =
                            ColorStateList.valueOf(view.context.getColor(R.color.green))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @BindingAdapter("android:rankTextTint")
        @JvmStatic
        fun setRankColor(view: TextView, color: MovieGrade) {
            try {
                when (color) {
                    MovieGrade.LOW -> {
                        view.setTextColor(view.context.getColor(R.color.red))
                    }

                    MovieGrade.MEDIOCRE -> {
                        view.setTextColor(view.context.getColor(R.color.orange))
                    }

                    MovieGrade.HIGH -> {
                        view.setTextColor(view.context.getColor(R.color.green))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @BindingAdapter("android:setImage")
        @JvmStatic
        fun setImage(view: ImageView, url: String) {
            try {
                Glide.with(view.context)
                    .load(url)
                    .placeholder(R.drawable.image)
                    .transform(CenterCrop(), RoundedCorners(42))
                    .error(R.drawable.broken_image)
                    .into(view)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}