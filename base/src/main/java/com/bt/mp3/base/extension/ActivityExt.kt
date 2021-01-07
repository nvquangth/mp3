package com.bt.mp3.base.extension

import android.app.Activity
import android.view.View
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.bt.mp3.base.model.AlertExceptionItem
import com.bt.mp3.base.model.DialogExceptionItem
import com.bt.mp3.base.model.SnackBarExceptionItem
import com.bt.mp3.base.model.ToastExceptionItem
import com.bt.mp3.entity.exception.CleanExceptionType


fun Activity.showToastException(
    e: ToastExceptionItem
) {
    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
}

fun Activity.showAlertException(
    e: AlertExceptionItem
) {
    MaterialAlertDialogBuilder(this ?: return)
        .setTitle(e.title)
        .setMessage(e.message)
        .setPositiveButton(e.positiveButton) { _, _ ->
        }
        .show()
}

fun Activity.showSnackBarException(
    e: SnackBarExceptionItem,
    view: View
) {
    Snackbar.make(view, e.message ?: "", Snackbar.LENGTH_SHORT)
        .show()
}

fun Activity.showDialogException(
    e: DialogExceptionItem,
    positiveAction: (() -> Unit)? = null,
    negativeAction: (() -> Unit)? = null
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(e.title)
        .setMessage(e.message)
        .setPositiveButton(e.positiveButton) { _, _ ->
            when (e.type) {
                CleanExceptionType.SERVER_MAINTENANCE -> finish()
                CleanExceptionType.APP_FORCE_UPDATE -> openAppOnPlayStore()
                else -> positiveAction?.invoke()
            }
        }
        .setNegativeButton(e.negativeButton) { _, _ ->
            negativeAction?.invoke()
        }
        .setCancelable(false)
        .show()
}

fun Activity.openAppOnPlayStore() {

}