package com.bt.mp3.ui.mymusic.recentsong

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bt.base.ui.BaseFragment
import com.bt.mp3.R
import com.bt.mp3.databinding.FragmentRecentSongBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recent_song.*

@AndroidEntryPoint
class RecentSongFragment : BaseFragment<FragmentRecentSongBinding, RecentSongViewModel>() {

    companion object {

        fun newInstance() = RecentSongFragment()
    }

    override val viewModel: RecentSongViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_recent_song

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val songAdapter = RecentSongAdapter()
        recyclerRecentSong.apply {
            adapter = songAdapter
        }

        with(viewModel) {
            recentSongs.observe(viewLifecycleOwner) {
                songAdapter.submitList(it.toMutableList())
            }
        }
    }
}
