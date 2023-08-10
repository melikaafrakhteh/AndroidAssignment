package ir.miare.androidcodechallenge.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.asResult(): Flow<ViewResource<T>> {
    return this
        .map<T, ViewResource<T>> {
            ViewResource.Success(it)
        }
        .onStart { emit(ViewResource.Loading()) }
        .catch { emit(ViewResource.Error(it.toString())) }
}