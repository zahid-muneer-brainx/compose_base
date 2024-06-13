package com.brainxtech.todolist.datasource.appUtils

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


sealed class BaseUiEvents {
    data class ShowToast(val message: Any) : BaseUiEvents()
    data class ShowDialog(val isError:Boolean,val message: Any) : BaseUiEvents()
}

object Events{
    private val _baseEventFlow = Channel<BaseUiEvents>()
    val baseEventFlow = _baseEventFlow.receiveAsFlow()

    suspend fun updateBaseEvent(event: BaseUiEvents) {
        _baseEventFlow.send(event)
    }
}