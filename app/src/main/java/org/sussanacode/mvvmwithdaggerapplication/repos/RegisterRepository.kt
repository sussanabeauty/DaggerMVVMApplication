package org.sussanacode.mvvmwithdaggerapplication.repos


import io.reactivex.Observable
import org.sussanacode.mvvmwithdaggerapplication.entity.request.RegisterRequestData
import org.sussanacode.mvvmwithdaggerapplication.entity.response.RegisterResponseData
import javax.inject.Inject

class RegisterRepository @Inject constructor(val registerNetwork: RegisterNetwork) {

    fun fetchData(registerRequestData: RegisterRequestData): Observable<RegisterResponseData> {
        return registerNetwork.registerUser(registerRequestData)

        //        return RegisterNetwork().registerUser(registerRequestData)
    }



}