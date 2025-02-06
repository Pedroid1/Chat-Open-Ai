package com.example.chatopenai.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import com.example.chatopenai.R
import com.google.android.material.R.attr.colorOnBackground
import com.google.android.material.color.MaterialColors

class CustomTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CustomTextView).apply {
            setTextType(
                EnumTextType.getTextType(
                    this.getInt(
                        R.styleable.CustomTextView_textType,
                        EnumTextType.BODY1.ordinal
                    )
                )
            )
            setupTitleAccessibility(this.getBoolean(R.styleable.CustomTextView_isTitle, false))
            setupPriorityText(
                EnumTextViewPriority.getTextPriority(
                    this.getInt(
                        R.styleable.CustomTextView_textPriority,
                        EnumTextViewPriority.SECONDARY.ordinal
                    )
                )
            )
        }.recycle()
        this.letterSpacing = 0.05f
    }

    private fun setupPriorityText(textPriority: EnumTextViewPriority) {
        when(textPriority) {
            EnumTextViewPriority.PRIMARY -> {
                this.setTextColor(MaterialColors.getColor(this, colorOnBackground))
            }
            EnumTextViewPriority.SECONDARY -> {
                //PATTERN COLOR
            }
        }
    }

    private fun setupTitleAccessibility(isTitle: Boolean) {
        ViewCompat.setAccessibilityHeading(this, isTitle);
    }

    private fun setTextType(type: EnumTextType) {
        when (type) {
            EnumTextType.TITLE1 -> {
                val font = ResourcesCompat.getFont(this.context, R.font.montserrat_semibold)
                this.typeface = font
                this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            }
            EnumTextType.TITLE2 -> {
                val font = ResourcesCompat.getFont(this.context, R.font.montserrat_semibold)
                this.typeface = font
                this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
            }
            EnumTextType.SUBTITLE1 -> {
                val font = ResourcesCompat.getFont(context, R.font.montserrat_medium);
                this.typeface = font
                this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
            }
            EnumTextType.SUBTITLE2 -> {
                val font = ResourcesCompat.getFont(context, R.font.montserrat_medium)
                this.typeface = font
                this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11f)
            }
            EnumTextType.BODY1 -> {
                val font = ResourcesCompat.getFont(context, R.font.montserrat_regular)
                this.typeface = font
                this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            }
            EnumTextType.BODY2 -> {
                val font = ResourcesCompat.getFont(context, R.font.montserrat_regular)
                this.typeface = font
                this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
            }
        }
    }

    enum class EnumTextType {
        TITLE1,
        TITLE2,
        SUBTITLE1,
        SUBTITLE2,
        BODY1,
        BODY2;

        companion object {
            @JvmStatic
            fun getTextType(index: Int): EnumTextType {
                return values()[index]
            }
        }
    }

    enum class EnumTextViewPriority {
        PRIMARY,
        SECONDARY;

        companion object {
            @JvmStatic
            fun getTextPriority(index: Int): EnumTextViewPriority {
                return values()[index]
            }
        }
    }
}