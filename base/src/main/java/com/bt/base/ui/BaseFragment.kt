package com.bt.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.bt.base.BR
import com.bt.base.R
import com.bt.base.model.AlertExceptionItem
import com.bt.base.model.DialogExceptionItem
import com.bt.base.model.SnackBarExceptionItem
import com.bt.base.model.ToastExceptionItem
import com.bt.base.extension.mapToExceptionItem
import com.bt.base.extension.showAlertException
import com.bt.base.extension.showDialogException
import com.bt.base.extension.showSnackBarException

abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    lateinit var viewBinding: ViewBinding

    abstract val viewModel: ViewModel

    @get:LayoutRes
    abstract val layoutRes: Int

    var loadingDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.apply {
            setVariable(BR.viewModel, viewModel)
            root.isClickable = true
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }

        with(viewModel) {
            exceptionEvent.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let {
                    when (val e = it.mapToExceptionItem(requireContext())) {
                        is AlertExceptionItem -> {
                            showAlertException(e)
                        }

                        is SnackBarExceptionItem -> {
                            showSnackBarException(e, view)
                        }

                        is DialogExceptionItem -> {
                            showDialogException(e)
                        }

                        is ToastExceptionItem -> {
                        }
                    }
                }
            }

            showLoadingEvent.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let {
                    showDialogLoading()
                }
            }

            hideLoadingEvent.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let {
                    hideDialogLoading()
                }
            }
        }
    }

    fun showDialogLoading() {
        if (loadingDialog?.isShowing != true) {
            MaterialAlertDialogBuilder(requireContext()).apply {
                setView(R.layout.layout_loading)
            }.create().apply {
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                loadingDialog = this

                show()
            }
        }
    }

    fun hideDialogLoading() {
        if (loadingDialog?.isShowing == true) {
            loadingDialog?.dismiss()
        }
    }
}
