package com.example.chatopenai.common

import android.app.Service
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

fun View.hideKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.GONE
}

fun View.setupLoadingView(active: Boolean) {
    if (active) this.toVisible() else this.toGone()
}

fun View.showSnackBar(message: String, timeLength: Int, anchorView: Int? = null) {
    val snackBar = Snackbar.make(this, message, timeLength)
    anchorView?.let { snackBar.setAnchorView(anchorView) }
    snackBar.show()
}

fun ImageView.loadImage(@DrawableRes resId: Int) = Picasso.get().load(resId).into(this)

fun ImageView.loadImage(url: String) = Picasso.get().load(url).priority(Picasso.Priority.HIGH).into(this)