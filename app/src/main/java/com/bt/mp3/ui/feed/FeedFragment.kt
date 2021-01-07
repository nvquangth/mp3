package com.bt.mp3.ui.feed

import androidx.fragment.app.viewModels
import com.bt.mp3.R
import com.bt.mp3.base.ui.BaseFragment
import com.bt.mp3.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding, FeedViewModel>() {

    override val viewModel: FeedViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_feed
}
