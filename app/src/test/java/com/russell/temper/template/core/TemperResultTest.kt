package com.russell.temper.template.core

import com.russell.temper.template.BaseUnitTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class TemperResultTest : BaseUnitTest() {
    @Test
    fun `when the result is successful the success function should get called`() {

        // Given
        var functionWasCalled = false
        val testFunction: (Boolean) -> Unit = { functionWasCalled = true }
        val temperResult = TemperResult.Success(functionWasCalled)

        // When
        temperResult.onSuccess(testFunction)

        // Then
        functionWasCalled shouldBeEqualTo true
    }

    @Test
    fun `when the result fails with an TemperException it should be forwarded`() {

        // Given
        val expectedException = Failure.CouldNotConnect
        var resultException: Failure? = null
        val temperResult = TemperResult.Error(Failure.CouldNotConnect)

        val testFunction: (Failure) -> Unit = { resultException = it }

        // When
        temperResult.onFailure(testFunction)

        // Then
        resultException shouldBeEqualTo expectedException
    }

    @Test
    fun `when the result fails with a socket timeout the correct temper exception should return`() {

        // Given
        val expectedException = Failure.Timeout
        var resultException: Failure? = null
        val temperResult = TemperResult.Error(SocketTimeoutException())

        val testFunction: (Failure) -> Unit = { resultException = it }

        // When
        temperResult.onFailure(testFunction)

        // Then
        resultException shouldBeEqualTo expectedException
    }

    @Test
    fun `when the result fails with an unknown host the correct temper exception should return`() {

        // Given
        val expectedException = Failure.NoInternet
        var resultException: Failure? = null
        val temperResult = TemperResult.Error(UnknownHostException())

        val testFunction: (Failure) -> Unit = { resultException = it }

        // When
        temperResult.onFailure(testFunction)

        // Then
        resultException shouldBeEqualTo expectedException
    }

    @Test
    fun `when the result fails with an unknown error the correct temper exception should return`() {

        // Given
        val expectedException = Failure.Unknown
        var resultException: Failure? = null
        val temperResult = TemperResult.Error(Exception())

        val testFunction: (Failure) -> Unit = { resultException = it }

        // When
        temperResult.onFailure(testFunction)

        // Then
        resultException shouldBeEqualTo expectedException
    }
}
