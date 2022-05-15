package com.example.movieapplatest.data.remote

import com.google.gson.JsonElement
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface APIService {

    @GET
    fun getRequest(
        @Url api: String,
        @HeaderMap headers: HashMap<String, String> = HashMap(),
        @QueryMap params: HashMap<String, Any> = HashMap()
    ): Observable<JsonElement>

    @GET
    fun getRequestCompletable(
        @Url api: String,
        @HeaderMap headers: HashMap<String, String> = HashMap(),
        @QueryMap params: HashMap<String, Any> = HashMap()
    ): Completable

    @POST
    fun postResponseBody(
        @Url api: String,
        @HeaderMap headers: HashMap<String, String> = HashMap(),
        @Body params: HashMap<String, Any> = HashMap()
    ): Observable<ResponseBody>

    @POST
    fun postRequest(
        @Url api: String,
        @HeaderMap headers: HashMap<String, String> = HashMap(),
        @Body params: HashMap<String, Any> = HashMap()
    ): Observable<JsonElement>

    @POST
    fun postRequestCompletable(
        @Url api: String,
        @HeaderMap headers: HashMap<String, String> = HashMap(),
        @Body params: HashMap<String, Any> = HashMap()
    ): Completable

}