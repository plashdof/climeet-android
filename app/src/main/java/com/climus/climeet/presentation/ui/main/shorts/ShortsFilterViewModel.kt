package com.climus.climeet.presentation.ui.main.shorts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.climus.climeet.presentation.ui.main.shorts.bottomsheet.selectsector.SelectedSector
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShortsFilterViewModel @Inject constructor(): ViewModel() {

    private val _applyFilter = MutableSharedFlow<SelectedSector>()
    val applyFilter: SharedFlow<SelectedSector> = _applyFilter.asSharedFlow()

    var selectedSector = SelectedSector()

    fun setFilterInfo(sectorInfo: SelectedSector){
        selectedSector = sectorInfo
        viewModelScope.launch {
            _applyFilter.emit(sectorInfo)
        }
    }

}