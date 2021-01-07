package com.bt.mp3.ui.mymusic

import androidx.fragment.app.viewModels
import com.bt.base.ui.BaseFragment
import com.bt.mp3.R
import com.bt.mp3.databinding.FragmentMyMusicBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyMusicFragment : BaseFragment<FragmentMyMusicBinding, MyMusicViewModel>() {

    override val viewModel: MyMusicViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_my_music
}
