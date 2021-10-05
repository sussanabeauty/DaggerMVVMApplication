package org.sussanacode.mvvmwithdaggerapplication.entity.request

data class RegisterRequestData(
    val firstname: String,
    val mobile: String,
    val email: String,
    val password: String
)
