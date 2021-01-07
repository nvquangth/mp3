package com.bt.base.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.bt.base.model.AlertExceptionItem
import com.bt.base.model.DialogExceptionItem
import com.bt.base.model.SnackBarExceptionItem
import com.bt.base.model.ToastExceptionItem
import com.bt.mp3.entity.exception.CleanExceptionType

fun Fragment.hideKeyBoard() {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view!!.windowToken, 0)
}

fun Fragment.showKeyBoard() {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun Fragment.showToastException(
    e: ToastExceptionItem
) {
    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showAlertException(
    e: AlertExceptionItem
) {
    MaterialAlertDialogBuilder(context ?: return)
        .setTitle(e.title)
        .setMessage(e.message)
        .setPositiveButton(e.positiveButton) { _, _ ->
        }
        .show()
}

fun Fragment.showSnackBarException(
    e: SnackBarExceptionItem,
    view: View
) {
    Snackbar.make(view, e.message ?: "", Snackbar.LENGTH_SHORT)
        .show()
}

fun Fragment.showDialogException(
    e: DialogExceptionItem,
    positiveAction: (() -> Unit)? = null,
    negativeAction: (() -> Unit)? = null
) {
    MaterialAlertDialogBuilder(context ?: return)
        .setTitle(e.title)
        .setMessage(e.message)
        .setPositiveButton(e.positiveButton) { _, _ ->
            when (e.type) {
                CleanExceptionType.SERVER_MAINTENANCE -> activity?.finish()
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

fun Fragment.openAppOnPlayStore() {

}
