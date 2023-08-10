package ir.miare.androidcodechallenge.util

sealed class Either<out A> {
    data class Success<A>(val value: A) : Either<A>()
    data class Failure<A>(val error: Error) : Either<A>()
}