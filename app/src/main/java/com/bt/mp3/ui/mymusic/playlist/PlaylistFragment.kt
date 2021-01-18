package com.bt.mp3.ui.mymusic.playlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bt.base.model.Result
import com.bt.base.ui.BaseFragment
import com.bt.mp3.R
import com.bt.mp3.databinding.FragmentPlaylistBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist.*

@AndroidEntryPoint
class PlaylistFragment : BaseFragment<FragmentPlaylistBinding, PlaylistViewModel>() {

    companion object {

        fun newInstance() = PlaylistFragment()
    }

    override val viewModel: PlaylistViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_playlist

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playlistAdapter = PlaylistAdapter()
        recyclerPlaylist.apply {
            adapter = playlistAdapter
        }

        with(viewModel) {

            playlistsResult.observe(viewLifecycleOwner) {
                when (it) {
                    is Result.Success -> {
                        playlistAdapter.submitList(it.data.toMutableList())
                    }
                }
            }

            setPlaylistId("ZWZB969E")
        }
    }
}
