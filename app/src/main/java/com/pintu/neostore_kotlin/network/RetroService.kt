package com.pintu.neostore_kotlin.network

import com.pintu.neostore_kotlin.model.APIMsg
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetroService {

    @FormUrlEncoded
    @POST("users/login")
    fun createLogin(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<APIMsg>

    @FormUrlEncoded
    @POST("users/register")
    fun registerPost(
        @Field("first_name") first_name: String?,
        @Field("last_name") last_name: String?,
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("confirm_password") confirm_password: String?,
        @Field("gender") gender: String?,
        @Field("phone_no") phone_no: String?,
    ) :Call<APIMsg>

    @FormUrlEncoded
    @POST("users/forgot")
    fun forgotPost(@Field("email") email: String?): Call<APIMsg>


}