package com.jemy.blackstone.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jemy.blackstone.data.common.Resource
import com.jemy.blackstone.data.model.LatestResponse
import com.jemy.blackstone.data.repository.LatestRepository
import com.jemy.blackstone.utils.Constants
import com.jemy.blackstone.utils.extensions.addTo
import com.jemy.blackstone.utils.extensions.setError
import com.jemy.blackstone.utils.extensions.setLoading
import com.jemy.blackstone.utils.extensions.setSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: LatestRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _latest = MutableLiveData<Resource<LatestResponse>>()

    val latest: LiveData<Resource<LatestResponse>>
        get() = _latest


    fun getLatest() {
        repository.getLatest()
            .doOnSubscribe { _latest.setLoading() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ latestResource ->
                latestResource?.data?.let { response ->
                    if (response.success) {
                        _latest.setSuccess(response)
                    } else {
                        _latest.setError(Constants.Error.GENERAL)
                    }
                } ?: _latest.setError(
                    Constants.Error.GENERAL
                )
            }, { throwable ->
                _latest.setError(Constants.Error.GENERAL)
                Log.e("MainActivity", throwable.message ?: "unknown error")
            })
            .addTo(compositeDisposable)
    }
//
//    override fun onCleared() {
//        compositeDisposable.dispose()
//        super.onCleared()
//    }
}