package com.climus.climeet.presentation.ui.main.shorts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.climus.climeet.R
import com.climus.climeet.databinding.FragmentShortsBinding
import com.climus.climeet.presentation.base.BaseFragment
import com.climus.climeet.presentation.ui.main.shorts.adapter.ShortsThumbnailAdapter
import com.climus.climeet.presentation.ui.main.shorts.adapter.UpdatedFollowAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShortsFragment : BaseFragment<FragmentShortsBinding>(R.layout.fragment_shorts) {

    private val sharedViewModel: ShortsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = sharedViewModel

        binding.rvShortsThumbnail.adapter = ShortsThumbnailAdapter()
        binding.rvUpdatedFollow.adapter = UpdatedFollowAdapter()

        sharedViewModel.getUpdatedFollow()
        sharedViewModel.getPopularShorts(ShortsOption.NEW_SORT)
        addOnScrollListener()
    }

    private fun addOnScrollListener() {

        binding.layoutScrollview.setOnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == binding.layoutScrollview.getChildAt(0).measuredHeight - v.measuredHeight) {
                sharedViewModel.callNextList()
            }
        }
    }
}