package com.jemy.blackstone.data.common

class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val message: String? = null
)