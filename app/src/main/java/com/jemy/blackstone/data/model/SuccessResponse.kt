package com.jemy.blackstone.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SuccessResponse(
    @Json(name = "error") val error: Error? = null,
    @Json(name = "success") val success: Boolean
) {
    @JsonClass(generateAdapter = true)
    data class Error(
        @Json(name = "code") val code: Int,
        @Json(name = "type") val type: String
    )
}