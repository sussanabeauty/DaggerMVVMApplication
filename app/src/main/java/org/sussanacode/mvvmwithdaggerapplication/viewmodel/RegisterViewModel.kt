package org.sussanacode.mvvmwithdaggerapplication.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.sussanacode.mvvmwithdaggerapplication.entity.request.RegisterRequestData
import org.sussanacode.mvvmwithdaggerapplication.entity.response.RegisterResponseData
import org.sussanacode.mvvmwithdaggerapplication.repos.RegisterNetwork
import org.sussanacode.mvvmwithdaggerapplication.repos.RegisterRepository

class RegisterViewModel: ViewModel() {

//    @Inject
//    lateinit var repo : RegisterRepository

    val firstname =  ObservableField<String>("")
    val mobile =  ObservableField<String>("")
    val email = ObservableField<String>("")
    val password = ObservableField<String>("")

    val nameError = ObservableField<String>()
    val mobileError = ObservableField<String>()
    val emailError = ObservableField<String>()
    val passwordError = ObservableField<String>()

    val registerResponse = MutableLiveData<RegisterResponseData>()

    val success = MutableLiveData<String>()

    val error = MutableLiveData<String>()
    val processing = ObservableField<Boolean>()



    fun registerUser(){

        var hasError = false


        firstname.get()?.let {
            if(it.isEmpty()) {
                nameError.set("Enter first name")
                hasError = true
            } else {
                nameError.set(null)
            }
        }

        mobile.get()?.let {
            if(it.isEmpty()) {
                mobileError.set("Enter mobile")
                hasError = true
            } else {
                mobileError.set(null)
            }
        }


        email.get()?.let {
            if(it.isEmpty()) {
                emailError.set("Please enter email id")
                hasError = true
            } else {
                emailError.set(null)
            }
        }

        password.get()?.let {
            if(it.isEmpty()) {
                passwordError.set("Please enter password")
                hasError = true
            } else {
                passwordError.set(null)
            }
        }

        if(hasError) {
            return
        }


        val registerRequest = RegisterRequestData(firstname.get()?:"NA", mobile.get()?:"NA", email.get()?:"NA", password.get()?:"NA")
        val observableRegisteruser = RegisterRepository(RegisterNetwork()).fetchData(registerRequest)

        observableRegisteruser.subscribeOn(Schedulers.io()).observeOn(mainThread())
            .subscribeWith(registerUserObservers())

    }

    private fun registerUserObservers(): Observer<RegisterResponseData> {
        return object: Observer<RegisterResponseData>{
            override fun onSubscribe(d: Disposable) {
                Log.d("onSubscribe", d.toString())
            }

            override fun onNext(t: RegisterResponseData) {
                //t.data
                success.postValue("Registration was success")

                t.error?.let { hasError ->

                    if(hasError){
                        Log.d("Register user failed", "Register was not successful", )
                    }else{
                        success.postValue("Registration was success")
                        t?.message?.let { message ->  Log.d("Register was successful", message)}
                    }
                }

//                //t.data
//                t.error?.let { hasError ->
//                    success.postValue("Registration was success")
//                    if(hasError){
//                        t?.message?.let { message ->  Log.d("Register was successful", message)}
//                    }else{
//                        Log.d("Register user failed", "Register was not successful", )
//                    }
//                }
            }

            override fun onError(e: Throwable) {
                error.postValue("Failed to register user")
                //Log.d("onError", e.toString())
            }

            override fun onComplete() {
                Log.d("onComplete", "Task completed")
            }
        }
    }
}