package com.bt.base.extension

import com.google.android.material.textfield.TextInputLayout
import com.bt.base.model.Validation

fun <T> TextInputLayout.setValidation(validationData: Validation<T>) {
    this.error =
        when (validationData) {
            is Validation.Invalid -> validationData.error.message
            else -> null
        }
}
