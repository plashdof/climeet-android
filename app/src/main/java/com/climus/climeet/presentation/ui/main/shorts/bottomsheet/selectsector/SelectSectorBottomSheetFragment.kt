package com.climus.climeet.presentation.ui.main.shorts.bottomsheet.selectsector

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.climus.climeet.R
import com.climus.climeet.databinding.FragmentSelectSectorBottomSheetBinding
import com.climus.climeet.presentation.base.BaseFragment
import com.climus.climeet.presentation.ui.main.shorts.ShortsFilterViewModel

class SelectSectorBottomSheetFragment: BaseFragment<FragmentSelectSectorBottomSheetBinding>(R.layout.fragment_select_sector_bottom_sheet) {

    private val shortsFilterViewModel: ShortsFilterViewModel by activityViewModels()
    private val viewModel: SelectSectorBottomSheetViewModel by viewModels()
    private val args: SelectSectorBottomSheetFragmentArgs by navArgs()
    private val cragId by lazy { args.cragId }
    private val cragName by lazy { args.cragName }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setCragInfo(cragId, cragName)
        initEventObserve()
    }

    private fun initEventObserve(){
        repeatOnStarted {
            viewModel.event.collect{
                when(it){
                    is SelectSectorBottomSheetEvent.NavigateToBack -> findNavController().navigateUp()
                }
            }
        }
    }

}