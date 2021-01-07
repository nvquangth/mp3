package com.bt.mp3.ui.chart

import androidx.fragment.app.viewModels
import com.bt.base.ui.BaseFragment
import com.bt.mp3.R
import com.bt.mp3.databinding.FragmentChartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartFragment : BaseFragment<FragmentChartBinding, ChartViewModel>() {

    override val viewModel: ChartViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_chart
}
