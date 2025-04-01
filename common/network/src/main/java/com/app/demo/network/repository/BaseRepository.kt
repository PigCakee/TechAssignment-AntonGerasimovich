package com.app.demo.network.repository

import android.util.Log
import com.app.demo.network.result.NetworkResult
import retrofit2.Response
import kotlin.coroutines.cancellation.CancellationException

abstract class BaseRepository {

    suspend fun <T> call(call: suspend () -> Response<T>): NetworkResult<T, String> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResult.Success(data = it, code = response.code())
                } ?: run {
                    Log.d("API Empty success","Empty body for successful response: code ${response.code()}")
                    NetworkResult.EmptySuccess
                }
            } else {
                val errorBody = response.errorBody()?.string().orEmpty()
                Log.e("HTTP Error: ", "HTTP error: code ${response.code()}, error: $errorBody")
                NetworkResult.HttpError(response.code().toString(), errorBody)
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("API Error: ", e.message.toString())
            NetworkResult.Error(e.message ?: "Unknown error")
        }
    }
}