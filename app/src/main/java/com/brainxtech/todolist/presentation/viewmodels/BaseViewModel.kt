package com.brainxtech.todolist.presentation.viewmodels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(), DefaultLifecycleObserver {

    // region lifecycle
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)

    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)

    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)

    }
    // end region
}