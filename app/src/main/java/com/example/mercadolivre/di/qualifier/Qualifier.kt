package com.example.mercadolivre.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AccessClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MLClient