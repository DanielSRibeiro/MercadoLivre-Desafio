plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {

    //Retrofit and Gson
    api(libs.retrofit)
    api(libs.retrofit2.converter.gson)

    //OkHttp
    api(libs.okhttp)
    api(platform(libs.okhttp.bom))
    api(libs.logging.interceptor)

    // Javax inject
    implementation(libs.javax.inject)

    // Coroutines core
    api(libs.kotlinx.coroutines.core)

    // Paging3 Common
    implementation(libs.androidx.paging.common)
}