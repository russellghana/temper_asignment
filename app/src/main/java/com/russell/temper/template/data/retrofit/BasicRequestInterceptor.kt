package com.russell.temper.template.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class BasicRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val basicHeaderRequest = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(basicHeaderRequest)
    }
}
