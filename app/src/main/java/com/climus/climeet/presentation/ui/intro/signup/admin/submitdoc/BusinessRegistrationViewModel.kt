package com.climus.climeet.presentation.ui.intro.signup.admin.submitdoc

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

sealed class BusinessRegistrationEvent{
    data object NavigateToBack: BusinessRegistrationEvent()
    data object NavigateToNext: BusinessRegistrationEvent()
}

@HiltViewModel
class BusinessRegistrationViewModel @Inject constructor(): ViewModel() {

    private val _event = MutableSharedFlow<BusinessRegistrationEvent>()
    val event: SharedFlow<BusinessRegistrationEvent> = _event.asSharedFlow()




}