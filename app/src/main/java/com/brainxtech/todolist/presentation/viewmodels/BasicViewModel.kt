package com.brainxtech.todolist.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.brainxtech.todolist.presentation.events.BasicScreenEvent
import com.brainxtech.todolist.presentation.states.BasicScreenState
import com.brainxtech.todolist.presentation.uiEvents.BasicScreenUiEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class BasicViewModel(
private val ioDispatcher: CoroutineDispatcher,
) : BaseViewModel() {
    private val _state = MutableStateFlow(BasicScreenState())
    val state = _state.asStateFlow()

    private val _eventFlow = Channel<BasicScreenUiEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    private fun emitUIEvents(event: BasicScreenUiEvent){
        viewModelScope.launch {
            _eventFlow.send(event)
        }
    }

    fun onEventUpdate(event: BasicScreenEvent) {
        event.apply {
            when(this){
                else->Unit
            }
        }
    }
}