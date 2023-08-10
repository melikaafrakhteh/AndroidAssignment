package ir.miare.androidcodechallenge.util

interface Mapper<First, Second> {
    fun convertFirstObjectToSecond(first: First): Second
    fun convertSecondObjectToFirst(second: Second): First
}