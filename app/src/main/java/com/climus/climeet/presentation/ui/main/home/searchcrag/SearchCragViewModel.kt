package com.climus.climeet.presentation.ui.main.home.searchcrag

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.climus.climeet.data.model.BaseState
import com.climus.climeet.data.model.response.BestFollowGymSimpleResponse
import com.climus.climeet.data.model.response.BestRouteDetailInfoResponse
import com.climus.climeet.data.model.response.BestRouteSimpleResponse
import com.climus.climeet.data.model.response.ShortsSimpleResponse
import com.climus.climeet.data.repository.MainRepository
import com.climus.climeet.presentation.ui.intro.signup.climer.followcrag.FollowCragEvent
import com.climus.climeet.presentation.ui.intro.signup.climer.model.FollowCrag
import com.climus.climeet.presentation.ui.intro.signup.climer.toFollowCrag
import com.climus.climeet.presentation.ui.main.home.HomeUiState
import com.climus.climeet.presentation.ui.main.home.model.HomeGym
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchCragUiState(
    val routeList: List<BestRouteDetailInfoResponse> = emptyList(),
    val searchList: List<FollowCrag> = emptyList(),
    val progressState: Boolean = false,
    val emptyResultState: Boolean = false
)

sealed class SearchCragEvent{
    data class ShowToastMessage(val msg: String): SearchCragEvent()
}

@HiltViewModel
class SearchCragViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    private val _uiState = MutableStateFlow(SearchCragUiState())
    val uiState: StateFlow<SearchCragUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<FollowCragEvent>()
    val event: SharedFlow<FollowCragEvent> = _event.asSharedFlow()

    private var curJob: Job? = null

    val keyword = MutableStateFlow("")

    init {
        //observeKeyword()
    }

    fun getRouteRankingOrderSelectionCount() {
        viewModelScope.launch {
            repository.findRouteRankingOrderSelectionCount("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNSttYW5hZ2VyIiwiaWF0IjoxNzA2NzQzMjczLCJleHAiOjE3MDcxMDMyNzN9.6IKq29hpSLSPw06TVHoN-gq3EP24MjtYlDwirrrYr3U").let {
                when(it) {
                    is BaseState.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                routeList = it.body
                            )
                        }
                    }
                    is BaseState.Error -> {
                        it.msg // 서버 에러 메시지
                        Log.d("API", it.msg)
                    }
                }
            }
        }
    }

//    private fun observeKeyword() {
//        keyword.onEach {
//            if (it.isBlank()) {
//                _uiState.update { state ->
//                    state.copy(
//                        searchList = emptyList(),
//                        emptyResultState = false
//                    )
//                }
//            } else {
//                curJob?.cancel()
//
//                _uiState.update { state ->
//                    state.copy(
//                        progressState = true,
//                        emptyResultState = false
//                    )
//                }
//
//                curJob = viewModelScope.launch {
//                    delay(500)
//                    repository.searchGym(it).let { result ->
//                        when (result) {
//                            is BaseState.Success -> {
//                                if (result.body.isNotEmpty()) {
//                                    _uiState.update { state ->
//                                        state.copy(
//                                            searchList = result.body.map { item ->
//                                                item.toFollowCrag(it)
//                                            },
//                                            progressState = false
//                                        )
//                                    }
//                                } else {
//                                    _uiState.update { state ->
//                                        state.copy(
//                                            searchList = emptyList(),
//                                            progressState = false,
//                                            emptyResultState = true
//                                        )
//                                    }
//                                }
//                            }
//
//                            is BaseState.Error -> {
//                                _uiState.update { state ->
//                                    state.copy(
//                                        progressState = false,
//                                        emptyResultState = true
//                                    )
//                                }
//                                _event.emit(FollowCragEvent.ShowToastMessage(result.msg))
//                            }
//                        }
//                    }
//                }
//            }
//        }.launchIn(viewModelScope)
//    }

}