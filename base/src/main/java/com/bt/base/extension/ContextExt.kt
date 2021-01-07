package com.bt.base.extension

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.bt.base.R

var loadingDialog: Dialog? = null

fun Context?.showLoadingDialog(cancelable: Boolean = false, canceledOnTouchOutside: Boolean = false): AlertDialog? {

    return MaterialAlertDialogBuilder(this ?: return null)
        .setView(R.layout.layout_loading)
        .create().apply {
            setCancelable(cancelable)
            setCanceledOnTouchOutside(canceledOnTouchOutside)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            if (loadingDialog?.isShowing == true) loadingDialog?.dismiss()

            loadingDialog = this

            show()
        }
}

fun Context?.hideLoadingDialog() {
    if (loadingDialog?.isShowing == true) loadingDialog?.dismiss()
}
