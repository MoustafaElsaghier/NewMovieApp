package com.example.movieapplatest.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.example.movieapplatest.R

class UiUtils(val context: Context) {

    private var progressDialog: Dialog? = null

    fun showSuccessMsg(@NonNull msg: String) {
        val toast = Toast(context)
        val view = (context as Activity).layoutInflater.inflate(
            R.layout.layout_green_toast,
            FrameLayout(context)
        )
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.BOTTOM, 10, 150)

        (view.findViewById(R.id.tv_message) as TextView).text = msg
        toast.view = view
        toast.show()
    }

    fun showSuccessSnakeBar(msg: String) {
        showSuccessMsg(msg)
    }

    fun showErrorMsg(@NonNull msg: String) {
        if (msg.isNotEmpty()) {
            val toast = Toast(context)
            val view = (context as Activity).layoutInflater.inflate(
                R.layout.layout_red_toast,
                FrameLayout(context)
            )
            toast.duration = Toast.LENGTH_SHORT
            toast.setGravity(Gravity.BOTTOM, 10, 150)

            (view.findViewById(R.id.tv_message) as TextView).text = msg
            toast.view = view
            toast.show()
        }
    }

    fun showWarningMsg(@NonNull msg: String) {
        val toast = Toast(context)
        val view = (context as Activity).layoutInflater.inflate(
            R.layout.layout_yellow_toast,
            FrameLayout(context)
        )
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.BOTTOM, 10, 150)

        (view.findViewById(R.id.tv_message) as TextView).text = msg
        toast.view = view
        toast.show()
    }

    fun showLoading() {
        progressDialog?.show() ?: initProgressDialog()
    }

    fun hideLoading() {
        progressDialog?.dismiss()
    }

    private fun initProgressDialog() {
        progressDialog = Dialog(context, R.style.ThemeDialog)
        progressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog?.setCancelable(false)
        progressDialog?.setCanceledOnTouchOutside(false)
        progressDialog?.setContentView(R.layout.layout_progress_dialog)
        progressDialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        progressDialog?.show()
    }

}