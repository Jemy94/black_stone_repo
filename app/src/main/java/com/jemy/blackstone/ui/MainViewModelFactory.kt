package com.jemy.blackstone.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jemy.blackstone.data.repository.LatestRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val repository: LatestRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name need ${MainViewModel::class.java.simpleName} instance")
    }

}