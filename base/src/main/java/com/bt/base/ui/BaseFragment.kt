package com.bt.base.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    private var _viewBinding: ViewBinding? = null

    // FIXME: restrict to protected modifier and correct throw expression (create new class?)
    val viewBinding: ViewBinding
        get() = _viewBinding ?: throw IllegalStateException(
            "BaseFragment#viewBinding is only valid between BaseFragment#onCreateView(..) " +
                "and BaseFragment#onDestroyView(..)"
        )

    abstract val viewModel: ViewModel

    @get:LayoutRes
    abstract val layoutRes: Int

    // FIXME: this can be private
    var loadingDialog: AlertDialog? = null

    // FIXME: add final modifier
    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<ViewBinding>(inflater, layoutRes, container, false).run {
            _viewBinding = this
            root
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.apply {
            setVariable(BR.viewModel, viewModel)
            root.isClickable = true
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }

        observeLiveData()
    }

    @CallSuper
    open fun observeLiveData() {
        with(viewModel) {
            exceptionEvent.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let { cleanException ->
                    val view = viewBinding.root
                    when (val e = cleanException.mapToExceptionItem(requireContext())) {
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

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        // release view
        _viewBinding = null
    }

    // FIXME: this can be private
    @MainThread
    fun showDialogLoading(cancelable: Boolean = false, canceledOnTouchOutside: Boolean = false) {
        if (loadingDialog?.isShowing != true) {
            MaterialAlertDialogBuilder(requireContext()).apply {
                background = ColorDrawable(Color.TRANSPARENT)
                setView(R.layout.layout_loading_new)
            }.create().apply {
                setCancelable(cancelable)
                setCanceledOnTouchOutside(canceledOnTouchOutside)
                loadingDialog = this

                show()
            }
        }
    }

    // FIXME: this can be private
    @MainThread
    fun hideDialogLoading() {
        if (loadingDialog?.isShowing == true) {
            loadingDialog?.dismiss()
        }
    }
}
