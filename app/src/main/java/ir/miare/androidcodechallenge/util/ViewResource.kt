package ir.miare.androidcodechallenge.util

sealed class ViewResource<out T> {
    class Loading<T> : ViewResource<T>()
    data class Success<T>(val data: T) : ViewResource<T>()
    data class Error<T> constructor(val error: String) : ViewResource<T>()
    object NotAvailable : ViewResource<Nothing>()
}