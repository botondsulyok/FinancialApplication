package com.example.financialapplication.ui.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}