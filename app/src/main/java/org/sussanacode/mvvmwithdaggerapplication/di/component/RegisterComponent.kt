package org.sussanacode.mvvmwithdaggerapplication.di.component

import dagger.Component
import org.sussanacode.mvvmwithdaggerapplication.MainActivity
import org.sussanacode.mvvmwithdaggerapplication.di.module.RegisterModule
import org.sussanacode.mvvmwithdaggerapplication.repos.RegisterNetwork
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(RegisterModule::class) )
interface RegisterComponent {

//    fun inject(mainActivity: MainActivity){}

    fun inject(datasource: RegisterNetwork)
}