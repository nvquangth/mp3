package com.bt.mp3.ui.chart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
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

        val data = listOf(
            listOf(
                0L to 5838L,
                1L to 6863L,
                2L to 7606L,
                3L to 6326L,
                4L to 6058L,
                5L to 6097L,
                6L to 6912L,
                7L to 7972L,
                8L to 7059L,
                9L to 7831L,
                10L to 9695L,
                11L to 10572L,
                12L to 10562L
            ),
            listOf(
                0L to 11940L,
                1L to 15209L,
                2L to 15065L,
                3L to 13371L,
                4L to 12321L,
                5L to 12644L,
                6L to 14519L,
                7L to 15801L,
                8L to 14499L,
                9L to 14365L,
                10L to 15010L,
                11L to 16467L,
                12L to 15746L
            ),
            listOf(
                0L to 10830L,
                1L to 12838L,
                2L to 12652L,
                3L to 11273L,
                4L to 10943L,
                5L to 11256L,
                6L to 12429L,
                7L to 13148L,
                8L to 11573L,
                9L to 11394L,
                10L to 11849L,
                11L to 13199L,
                12L to 12499L
            ),
        )

        lineChart.setData(data)

        viewHeader.setOnClickListener {
        }

        imagePlay.setOnClickListener {
        }
    }
}
