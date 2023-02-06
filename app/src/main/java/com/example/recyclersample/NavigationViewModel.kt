package com.example.recyclersample

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class NavigationViewModel(application: Application):AndroidViewModel(application) {

    override fun onCleared() {
        super.onCleared()
        Log.d("NavigationViewModel", "onCleared: ")
    }
}