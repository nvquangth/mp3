package com.bt.base.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.observe
import androidx.preference.PreferenceManager
import com.bt.base.BR
import com.bt.base.R
import com.bt.base.extension.mapToExceptionItem
import com.bt.base.extension.showAlertException
import com.bt.base.extension.showDialogException
import com.bt.base.extension.showSnackBarException
import com.bt.base.model.AlertExceptionItem
import com.bt.base.model.DialogExceptionItem
import com.bt.base.model.SnackBarExceptionItem
import com.bt.base.model.ToastExceptionItem
import com.bt.base.uikit.ThemeHelper

abstract class BaseActivity<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : AppCompatActivity() {

    lateinit var viewBinding: ViewBinding

    abstract val viewModel: ViewModel

    abstract val sharedViewModel: BaseViewModel

    @get:LayoutRes
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        applyTheme()
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, layoutRes)
        viewBinding.apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }

        with(viewModel) {
            exceptionEvent.observe(this@BaseActivity) {
                it.getContentIfNotHandled()?.let {
                    when (val e = it.mapToExceptionItem(this@BaseActivity)) {
                        is AlertExceptionItem -> {
                            showAlertException(e)
                        }

                        is SnackBarExceptionItem -> {
                            showSnackBarException(e, viewBinding.root)
                        }

                        is DialogExceptionItem -> {
                            showDialogException(e)
                        }

                        is ToastExceptionItem -> {
                        }
                    }
                }
            }
        }

        with(sharedViewModel) {
            exceptionEvent.observe(this@BaseActivity) {
                it.getContentIfNotHandled()?.let {
                    when (val e = it.mapToExceptionItem(this@BaseActivity)) {
                        is AlertExceptionItem -> {
                            showAlertException(e)
                        }

                        is SnackBarExceptionItem -> {
                            showSnackBarException(e, viewBinding.root)
                        }

                        is DialogExceptionItem -> {
                            showDialogException(e)
                        }

                        is ToastExceptionItem -> {
                        }
                    }
                }
            }
        }
    }

    private fun applyTheme() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        when (sharedPreferences.getString(ThemeHelper.THEME_PREF, ThemeHelper.COLOR_1) ?: ThemeHelper.COLOR_1) {
            ThemeHelper.COLOR_1 -> setTheme(R.style.Theme_MyApp_COLOR1)
            ThemeHelper.COLOR_2 -> setTheme(R.style.Theme_MyApp_COLOR2)
            ThemeHelper.COLOR_3 -> setTheme(R.style.Theme_MyApp_COLOR3)
            ThemeHelper.COLOR_4 -> setTheme(R.style.Theme_MyApp_COLOR4)
            ThemeHelper.COLOR_5 -> setTheme(R.style.Theme_MyApp_COLOR5)
            ThemeHelper.COLOR_6 -> setTheme(R.style.Theme_MyApp_COLOR6)
            ThemeHelper.COLOR_7 -> setTheme(R.style.Theme_MyApp_COLOR7)
            ThemeHelper.COLOR_8 -> setTheme(R.style.Theme_MyApp_COLOR8)
            else -> setTheme(R.style.Theme_MyApp_COLOR9)
        }

        when (sharedPreferences.getString(ThemeHelper.THEME_MODE_PREF, ThemeHelper.DEFAULT_MODE) ?: ThemeHelper.DEFAULT_MODE) {
            ThemeHelper.LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            ThemeHelper.DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }
}
