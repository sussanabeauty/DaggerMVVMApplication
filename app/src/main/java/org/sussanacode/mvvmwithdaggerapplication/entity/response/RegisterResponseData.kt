package org.sussanacode.mvvmwithdaggerapplication.entity.response

data class RegisterResponseData (
    val error: Boolean?,
    val message: String?,
    val data: Data?
)


data class Data(

    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val firstName: String,
    val mobile: String,
    val password: String

)
