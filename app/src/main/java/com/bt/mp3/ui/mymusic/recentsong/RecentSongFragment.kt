package com.bt.mp3.ui.mymusic.recentsong

import androidx.fragment.app.viewModels
import com.bt.base.ui.BaseFragment
import com.bt.mp3.R
import com.bt.mp3.databinding.FragmentRecentSongBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentSongFragment : BaseFragment<FragmentRecentSongBinding, RecentSongViewModel>() {

    companion object {

        fun newInstance() = RecentSongFragment()
    }

    override val viewModel: RecentSongViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_recent_song
}
