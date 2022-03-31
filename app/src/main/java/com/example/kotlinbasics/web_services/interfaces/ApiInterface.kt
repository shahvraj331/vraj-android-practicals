package com.example.kotlinbasics.web_services.interfaces

import com.example.kotlinbasics.commonFolder.utils.Constants
import com.example.kotlinbasics.web_services.data_classes.SingleUserData
import com.example.kotlinbasics.web_services.data_classes.NewUserDataModel
import com.example.kotlinbasics.web_services.data_classes.NewUserResponseModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("api/users/{id}")
    fun getData(@Path("id") userID: Int): Call<SingleUserData>

    @POST("api/users")
    fun addUser(@Body userData: NewUserDataModel): Call<NewUserResponseModel>

    companion object {
        fun getRetrofitObject(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
                .addInterceptor(
                    Interceptor {
                        val builder = it.request().newBuilder()
                        builder.header("Content-Type", "application/json")
                        return@Interceptor it.proceed(builder.build())
                    }
                )
            val okHttpClient = okHttpClientBuilder.build()

            return Retrofit.Builder()
                .baseUrl(Constants.REQ_RES_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}