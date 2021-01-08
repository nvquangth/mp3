package com.bt.mp3.ui.mymusic

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bt.base.ui.BaseFragment
import com.bt.mp3.R
import com.bt.mp3.databinding.FragmentMyMusicBinding
import com.bt.mp3.model.LibraryItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_my_music.*

@AndroidEntryPoint
class MyMusicFragment : BaseFragment<FragmentMyMusicBinding, MyMusicViewModel>() {

    override val viewModel: MyMusicViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_my_music

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val libraryAdapter = LibraryAdapter {
        }

        recyclerLibrary?.apply {
            adapter = libraryAdapter
            addItemDecoration(
                object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        val pos = parent.getChildAdapterPosition(view)

                        Log.d("quangnvxx", "$pos - ${getLibraries()[pos].title}")

                        when (pos % 2) {
                            0 -> {
                                outRect.apply {
                                    left = context.resources.getDimensionPixelOffset(R.dimen.dp_12)
                                    top = context.resources.getDimensionPixelOffset(R.dimen.dp_12)
                                    right = 0
                                    bottom = 0
                                }
                            }
                            else -> {
                                outRect.apply {
                                    left = context.resources.getDimensionPixelOffset(R.dimen.dp_12)
                                    top = context.resources.getDimensionPixelOffset(R.dimen.dp_6)
                                    right = 0
                                    bottom = context.resources.getDimensionPixelOffset(R.dimen.dp_12)
                                }
                            }
                        }

                        if (pos == parent.adapter?.itemCount?.minus(1) || pos == parent.adapter?.itemCount?.minus(2)) {
                            outRect.apply {
                                right = context.resources.getDimensionPixelOffset(R.dimen.dp_12)
                            }
                        }
                    }
                }
            )
        }

        libraryAdapter.setData(getLibraries())
    }

    private fun getLibraries(): List<LibraryItem> {
        return listOf(
            LibraryItem(1, getString(R.string.song), R.drawable.ic_my_songs_simple, 20),
            LibraryItem(2, getString(R.string.on_device), R.drawable.ic_my_downloaded_simple, 55),
            LibraryItem(3, getString(R.string.upload), R.drawable.ic_my_uploads_simple, 1),
            LibraryItem(4, getString(R.string.album), R.drawable.ic_my_albums_simple, 19),
            LibraryItem(5, getString(R.string.mv), R.drawable.ic_my_videos_simple, 0),
            LibraryItem(6, getString(R.string.artist), R.drawable.ic_my_artists_simple, 16),
        )
    }
}
