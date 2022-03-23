package com.android.emailverification.twilioemailauthentication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.emailverification.twilioemailauthentication.Backend.ResponseModel
import kotlin.collections.HashMap
/**
 * Created by Ahmed Sadiq
 */
class CustomViewModel(application: Application) : AndroidViewModel(application) {
    private var repo: Repository? = null
    var responseLiveData: MutableLiveData<ResponseModel>? = null
        private set
    fun init() {
        repo = Repository()
        responseLiveData = repo!!.dataModel
    }
    fun authenticate(params: HashMap<String, String>) {
        repo!!.authenticate(params)
    }
}