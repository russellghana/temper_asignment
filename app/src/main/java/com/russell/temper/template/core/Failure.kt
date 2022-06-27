package com.russell.temper.template.core

sealed class Failure : Exception() {
    object NoInternet : Failure()
    object Timeout : Failure()
    object CouldNotConnect : Failure()
    object Unknown : Failure()
}
