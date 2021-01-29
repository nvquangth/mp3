package com.bt.mp3.ui.chart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bt.base.ui.BaseFragment
import com.bt.mp3.R
import com.bt.mp3.databinding.FragmentChartBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.chart.*

@AndroidEntryPoint
class ChartFragment : BaseFragment<FragmentChartBinding, ChartViewModel>() {

    override val viewModel: ChartViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_chart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val songAdapter = SongChartAdapter()
        recyclerSong.apply {
            adapter = songAdapter
        }

        with(viewModel) {
            dataChart.observe(viewLifecycleOwner) {
                lineChart.setData(it)
            }
            songs.observe(viewLifecycleOwner) {
                songAdapter.submitList(it.toMutableList())
            }
        }

        viewHeader.setOnClickListener {
        }

        imagePlay.setOnClickListener {
        }
    }
}
