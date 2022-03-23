package com.android.emailverification.twilioemailauthentication

import androidx.lifecycle.MutableLiveData
import com.android.emailverification.twilioemailauthentication.Backend.APIClient
import com.android.emailverification.twilioemailauthentication.Backend.ResponseModel
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Created by Ahmed Sadiq
 */
class Repository {

    private val APIClient: APIClient
    val dataModel: MutableLiveData<ResponseModel>?

    fun authenticate(params: HashMap<String, String>) {
        APIClient.sendVerificationCode(params)
            .enqueue(object : Callback<ResponseModel?> {
                override fun onResponse(
                    call: Call<ResponseModel?>,
                    responseModel: Response<ResponseModel?>
                ) {
                    if (responseModel.body() != null) {
                        dataModel?.postValue(responseModel.body())
                    }
                }

                override fun onFailure(call: Call<ResponseModel?>, t: Throwable) {
                    dataModel?.postValue(null)
                }
            })
    }


    companion object {
        private const val BASE_URL = "Add your own Base URL here"
    }

    init {
        dataModel = MutableLiveData()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        /**
         * Retrofit client creator
         */
        APIClient = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(com.android.emailverification.twilioemailauthentication.Backend.APIClient::class.java)
    }
}