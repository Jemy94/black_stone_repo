package com.jemy.blackstone.data.repository

import com.jemy.blackstone.data.common.Resource
import com.jemy.blackstone.data.common.Validator
import com.jemy.blackstone.data.model.LatestResponse
import com.jemy.blackstone.data.remote.Api
import io.reactivex.Single
import javax.inject.Inject

class LatestRepository @Inject constructor(
    private val api: Api,
    private val validator: Validator
) {

    fun getLatest(): Single<Resource<LatestResponse>> {
        return api.getLatestCurrency()
            .map {
                validator.validateResponse(it) }
            .map {
                Resource(it.state, if (it.data == null) null else it.data, it.message) }
    }
}