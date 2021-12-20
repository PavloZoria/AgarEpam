package com.epam.agar.hackathon.agar_epam.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.epam.agar.hackathon.agar_epam.R
import java.lang.IllegalArgumentException

typealias OnTireStatusClickedListener = () -> Unit

class TireScanStatusComponent @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(ctx, attrs, defStyleAttr) {

    private var listener: OnTireStatusClickedListener? = null

    private var imageView: ImageView
    private var progressBar: ProgressBar

    var tireWearStatus: Status = Status.Unknown
        set(value) {
            when (value) {
                Status.Processing -> {
                    imageView.isVisible = false
                    progressBar.isVisible = true
                }
                Status.Unknown -> {
                    imageView.isVisible = false
                    progressBar.isVisible = false
                }
                else -> {
                    progressBar.isVisible = false
                    imageView.isVisible = true
                    imageView.setImageResource(getImageResFromTireStatus(value))
                }
            }
            imageView.isClickable = value == Status.Succeed
            field = value
        }

    init {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.status_component, this)
        imageView = findViewById(R.id.status_image_view)
        progressBar = findViewById(R.id.progress_bar)
        imageView.setOnClickListener { listener?.invoke() }
    }

    fun setOnStatusIconClickedListener(listener: OnTireStatusClickedListener?) {
        this.listener = listener
    }

    private fun getImageResFromTireStatus(status: Status): Int {
        return when (status) {
            Status.Succeed -> R.drawable.status_ok
            Status.Error -> R.drawable.status_exclamation
            Status.Unknown -> 0
            else -> throw IllegalArgumentException("No image res for tire status: $status")
        }
    }
}
