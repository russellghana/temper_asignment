package com.russell.temper.template.data.di.retrofit

import javax.inject.Qualifier

class InterceptorQualifiers {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BasicRequestInterceptor
}
