package com.mahesaiqbal.academy.utils

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity.CENTER
import androidx.appcompat.widget.AppCompatButton
import com.mahesaiqbal.academy.R

class MyButton : AppCompatButton {

    private var enabledBackground: Drawable? = null
    private var disabledBackground: Drawable? = null
    private var textColorInt: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        background = if (isEnabled) enabledBackground else disabledBackground
        setTextColor(textColorInt)
        textSize = 12f
        gravity = CENTER
    }

    private fun init() {
        val resources = resources
        enabledBackground = resources.getDrawable(R.drawable.bg_button)
        disabledBackground = resources.getDrawable(R.drawable.bg_button_disable)
        textColorInt = ContextCompat.getColor(context, android.R.color.background_light)
    }
}