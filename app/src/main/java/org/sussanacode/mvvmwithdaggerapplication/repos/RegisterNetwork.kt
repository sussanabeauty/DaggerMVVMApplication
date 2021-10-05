package org.sussanacode.mvvmwithdaggerapplication.repos

import io.reactivex.Observable
import org.sussanacode.mvvmwithdaggerapplication.api.ApiService
import org.sussanacode.mvvmwithdaggerapplication.di.component.DaggerRegisterComponent
import org.sussanacode.mvvmwithdaggerapplication.di.component.RegisterComponent
import org.sussanacode.mvvmwithdaggerapplication.di.module.RegisterModule
import org.sussanacode.mvvmwithdaggerapplication.entity.request.RegisterRequestData
import org.sussanacode.mvvmwithdaggerapplication.entity.response.RegisterResponseData
import javax.inject.Inject

class RegisterNetwork @Inject constructor(){


    @Inject
    lateinit var apiService: ApiService
    init{
        val daggerRegisterComponent: RegisterComponent = DaggerRegisterComponent.builder().registerModule(RegisterModule()).build()

        daggerRegisterComponent.inject(this)

    }

    fun registerUser(userRegisteration: RegisterRequestData): Observable<RegisterResponseData> {
        return apiService.register(userRegisteration)
    }

}