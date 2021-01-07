package com.bt.mp3.ui.discover

import androidx.fragment.app.viewModels
import com.bt.mp3.R
import com.bt.mp3.base.ui.BaseFragment
import com.bt.mp3.databinding.FragmentDiscoverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : BaseFragment<FragmentDiscoverBinding, DiscoverViewModel>() {

    override val viewModel: DiscoverViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_discover
}
