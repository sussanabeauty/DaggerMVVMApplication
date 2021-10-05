package org.sussanacode.mvvmwithdaggerapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.sussanacode.mvvmwithdaggerapplication.viewmodel.RegisterViewModel
import org.sussanacode.mvvmwithdaggerapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        setUpViewModel()
        setUpObservers()
    }



    private fun setUpObservers() {

        viewModel.success.observe(this){
            Log.d("Success", "Register successful")
        }


        viewModel.registerResponse.observe(this ){

            it.error?.let { hasError ->
                if(hasError){
                    it?.message?.let { message -> Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show() }

                }
            }
        }

        viewModel.error.observe(this) {
            Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
        }

    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewmodel = viewModel
    }
}