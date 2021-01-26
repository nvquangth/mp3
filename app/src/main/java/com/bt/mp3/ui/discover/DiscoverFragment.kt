package com.bt.mp3.ui.discover

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import com.bt.base.extension.toPx
import com.bt.base.ui.BaseFragment
import com.bt.mp3.R
import com.bt.mp3.databinding.FragmentDiscoverBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_discover.*

@AndroidEntryPoint
class DiscoverFragment : BaseFragment<FragmentDiscoverBinding, DiscoverViewModel>() {

    override val viewModel: DiscoverViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_discover

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bannerAdapter = SectionBannerAdapter()
        val sectionZmaAdapter = SectionZMAAdapter()
        val sectionPlaylistAdapter = SectionPlaylistAdapter()
        val sectionGenreAdapter = SectionGenreAdapter()
        val sectionNewReleaseAdapter = SectionNewReleaseAdapter()
        val sectionVideoAdapter = SectionVideoAdapter()
        val sectionSongAdapter = SectionSongAdapter()
        val concatAdapter = ConcatAdapter(
            sectionZmaAdapter,
            sectionNewReleaseAdapter,
            sectionSongAdapter,
            sectionGenreAdapter,
            sectionVideoAdapter,
            sectionPlaylistAdapter
        )

        pagerBanner?.apply {
            adapter = bannerAdapter
            setPageTransformer(MarginPageTransformer(12F.toPx(context)))
        }

        recyclerSection.apply {
            adapter = concatAdapter

            addItemDecoration(
                object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        val pos = parent.getChildAdapterPosition(view)
                        if (pos == 0) {
                            outRect.apply {
                                top = context.resources.getDimensionPixelOffset(R.dimen.dp_16)
                            }
                        }
                        outRect.apply {
                            bottom = context.resources.getDimensionPixelOffset(R.dimen.dp_16)
                        }
                    }
                }
            )
        }

        with(viewModel) {
            sectionBanner.observe(viewLifecycleOwner) {
                bannerAdapter.submitList(it.items?.toMutableList())
            }

            sectionZMA.observe(viewLifecycleOwner) {
                sectionZmaAdapter.submitList(it.toMutableList())
            }

            sectionPlaylist.observe(viewLifecycleOwner) {
                sectionPlaylistAdapter.submitList(it.toMutableList())
            }

            sectionGenre.observe(viewLifecycleOwner) {
                sectionGenreAdapter.submitList(it.toMutableList())
            }

            sectionNewRelease.observe(viewLifecycleOwner) {
                sectionNewReleaseAdapter.submitList(it.toMutableList())
            }

            sectionVideo.observe(viewLifecycleOwner) {
                sectionVideoAdapter.submitList(it.toMutableList())
            }

            sectionSong.observe(viewLifecycleOwner) {
                sectionSongAdapter.submitList(it.toMutableList())
            }

            isLoadMoreHomePage.observe(viewLifecycleOwner) {
                if (it) {
                    setPage(getCurrentPage() + 1)
                }
            }
        }
    }
}
