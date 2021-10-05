package org.sussanacode.mvvmwithdaggerapplication.viewmodel

import android.widget.EditText
import androidx.databinding.BindingAdapter

class CommonBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("error_text")
        fun setErrorText(editText: EditText, errorText: String?){
            editText.error = errorText
        }
    }
}