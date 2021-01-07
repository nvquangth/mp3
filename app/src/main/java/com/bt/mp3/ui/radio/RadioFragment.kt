package com.bt.mp3.ui.radio

import androidx.fragment.app.viewModels
import com.bt.mp3.R
import com.bt.mp3.base.ui.BaseFragment
import com.bt.mp3.databinding.FragmentRadioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioFragment : BaseFragment<FragmentRadioBinding, RadioViewModel>() {

    override val viewModel: RadioViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_radio
}
