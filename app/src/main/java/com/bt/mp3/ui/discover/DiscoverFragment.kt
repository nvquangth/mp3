package com.bt.mp3.ui.discover

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bt.base.ui.BaseFragment
import com.bt.mp3.R
import com.bt.mp3.databinding.FragmentDiscoverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : BaseFragment<FragmentDiscoverBinding, DiscoverViewModel>() {

    override val viewModel: DiscoverViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_discover

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
