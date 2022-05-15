package com.example.movieapplatest.ui.base

import androidx.lifecycle.ViewModel
import com.example.movieapplatest.R
import com.example.movieapplatest.utils.ResourcesUtil
import com.hadilq.liveevent.LiveEvent
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.json.JSONObject
import org.koin.java.KoinJavaComponent.inject
import timber.log.Timber
import java.io.IOException

open class BaseViewModel : ViewModel() {

    val errorMsgLiveData: LiveEvent<String> = LiveEvent()
    val successMsgLiveData: LiveEvent<String> = LiveEvent()
    val successSnakeBarLiveData: LiveEvent<String> = LiveEvent()
    val loadingLiveData: LiveEvent<Boolean> = LiveEvent()

    private val disposable: CompositeDisposable = CompositeDisposable()
    val resources: ResourcesUtil by inject(ResourcesUtil::class.java)

    fun addToDisposable(disposable: Disposable, dropRequest: Boolean = false) {

        if (dropRequest) {
            this.disposable.clear()
        }
        this.disposable.remove(disposable)
        this.disposable.add(disposable)
    }

    fun processError(throwable: Throwable) {
        loadingLiveData.value = false
        when (throwable) {
            is HttpException -> {
                errorMsgLiveData.value = getHttpErrorMessage(throwable)
            }

            is IOException -> {
                errorMsgLiveData.value = resources.getString(R.string.msg_connection_error)
            }

            else -> {
                errorMsgLiveData.value = resources.getString(R.string.msg_internal_error)
                Timber.e(throwable, "UNEXPECTED_ERROR")
            }

        }

    }


    fun getErrorsMessage(throwable: Throwable): HashMap<String, String> {
        val map: HashMap<String, String> = HashMap()
        when (throwable) {
            is HttpException -> {
                map.putAll(getHttpErrorsMessage(throwable))
                if (map.isEmpty())
                    errorMsgLiveData.value = resources.getString(R.string.msg_internal_error)
            }

            else -> {
                errorMsgLiveData.value = resources.getString(R.string.msg_internal_error)
                Timber.e(throwable, "UNEXPECTED_ERROR")
            }

        }
        return map
    }


    fun getErrorMessage(throwable: Throwable): String {
        var message: String = resources.getString(R.string.msg_internal_error)
        when (throwable) {
            is HttpException -> {
                message = getHttpErrorMessage(throwable)
            }
            else -> {
                errorMsgLiveData.value = resources.getString(R.string.msg_internal_error)
                Timber.e(throwable, "UNEXPECTED_ERROR")
            }

        }
        return message
    }

    private fun getHttpErrorsMessage(throwable: HttpException): HashMap<String, String> {
        val map: HashMap<String, String> = HashMap()

        try {
            val jsonObject = JSONObject(throwable.response().errorBody()!!.string())
            val iterator: Iterator<String> = jsonObject.getJSONObject("errors").keys()
            while (iterator.hasNext()) {
                val key = iterator.next()
                val msg = jsonObject.getJSONObject("errors").getJSONArray(key)[0] as String
                map.put(key, msg)
            }

        } catch (e: Exception) {
            Timber.e(e)
        }
        return map
    }

    private fun getHttpErrorMessage(throwable: HttpException): String {
        var msg = resources.getString(R.string.msg_internal_error)
        try {
            val jsonObject = JSONObject(throwable.response().errorBody()?.string() ?: "")
            if (jsonObject.has("errors")) {
                val iterator: Iterator<String> = jsonObject.getJSONObject("errors").keys()
                if (iterator.hasNext()) {
                    val key = iterator.next()
                    msg = jsonObject.getJSONObject("errors").getJSONArray(key)[0] as String
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        return msg
    }


    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}