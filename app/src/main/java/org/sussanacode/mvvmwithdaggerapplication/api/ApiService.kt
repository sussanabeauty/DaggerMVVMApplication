package org.sussanacode.mvvmwithdaggerapplication.api


import io.reactivex.Observable
import org.sussanacode.mvvmwithdaggerapplication.entity.request.RegisterRequestData
import org.sussanacode.mvvmwithdaggerapplication.entity.response.RegisterResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-type: application/json")
    @POST("auth/register")
    fun register(@Body registerUser: RegisterRequestData): Observable<RegisterResponseData>
}