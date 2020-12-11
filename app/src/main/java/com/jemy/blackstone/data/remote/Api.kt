package com.jemy.blackstone.data.remote

import com.jemy.blackstone.data.model.LatestResponse
import com.jemy.blackstone.utils.Constants
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(Endpoints.LATEST)
    fun getLatestCurrency(
        @Query("access_key") apiKey: String = Constants.API_KEY,
        @Query("symbols") symbols:String = "EGP,SAR,USD,AED "
    ): Single<Response<LatestResponse>>

}