package com.appetizercodingchallenge

object Versions {
    const val ktlint = "0.29.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.0.0"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.3"
    const val threeTenBp = "org.threeten:threetenbp:1.4.4"
    const val threeTenBpNoTzdb = "$threeTenBp:no-tzdb"
    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.2.4"
    const val chucker = "com.github.chuckerteam.chucker:library:3.2.0"
    const val mockito = "org.mockito:mockito-inline:2.25.0"
    const val store = "com.dropbox.mobile.store:store4:4.0.0-alpha06"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val imagePicker = "com.github.dhaval2404:imagepicker:1.7.3"

    object Kotlin {
        private const val version = "1.3.72"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.3.7"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        const val archCoreTesting = "androidx.arch.core:core-testing:2.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta7"
        const val appcompat = "androidx.appcompat:appcompat:1.1.0"
        const val coreKtx = "androidx.core:core-ktx:1.3.0"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
        const val viewpager = "androidx.viewpager2:viewpager2:1.0.0"
        const val swiperefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0-rc01"

        object Navigation {
            private const val version = "2.2.2"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Fragment {
            private const val version = "1.2.5"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
            const val fragmentTesting = "androidx.fragment:fragment-testing:$version"
        }

        object Lifecycle {
            private const val version = "2.2.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Paging {
            private const val version = "2.1.2"
            const val common = "androidx.paging:paging-common-ktx:$version"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
        }

        object Room {
            private const val version = "2.2.5"
            const val common = "androidx.room:room-common:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val testing = "androidx.room:room-testing:$version"
        }

        object Test {
            private const val version = "1.2.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2-rc01"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
        }

        object Work {
            private const val version = "2.3.4"
            const val runtimeKtx = "androidx.work:work-runtime-ktx:$version"
        }
    }

    object Anko {
        private const val version = "0.10.8"
        const val common = "org.jetbrains.anko:anko-commons:$version"
    }

    object Coil {
        private const val version = "0.11.0"
        const val coil = "io.coil-kt:coil:$version"
    }

    object Epoxy {
        private const val version = "3.11.0"
        const val epoxy = "com.airbnb.android:epoxy:$version"
        const val paging = "com.airbnb.android:epoxy-paging:$version"
        const val dataBinding = "com.airbnb.android:epoxy-databinding:$version"
        const val processor = "com.airbnb.android:epoxy-processor:$version"
    }

    object Glide {
        private const val version = "4.11.0"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
        const val glide = "com.github.bumptech.glide:glide:$version"
    }

    object Google {
        const val crashlytics = "com.google.firebase:firebase-crashlytics:17.0.1"
        const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:2.2.0"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics:17.4.3"
        const val firebaseMessaging = "com.google.firebase:firebase-messaging:20.2.1"
        const val material = "com.google.android.material:material:1.3.0-alpha01"
        const val truth = "com.google.truth:truth:0.42"
        const val gmsGoogleServices = "com.google.gms:google-services:4.3.3"
    }

    object Insetter {
        private const val version = "0.3.0"
        const val core = "dev.chrisbanes:insetter:$version"
        const val dbx = "dev.chrisbanes:insetter-dbx:$version"
        const val ktx = "dev.chrisbanes:insetter-ktx:$version"
    }

    object Junit5 {
        private const val version = "1.0.0"
        const val core = "de.mannodermaus.junit5:android-test-core:$version"
        const val runner = "de.mannodermaus.junit5:android-test-runner:$version"
        const val gradlePlugin = "de.mannodermaus.gradle.plugins:android-junit5:1.6.2.0"
    }

    const val jacoco = "org.jacoco:org.jacoco.core:0.8.1"

    object Koin {
        private const val version = "2.1.5"
        const val android = "org.koin:koin-android:$version"
        const val viewmodel = "org.koin:koin-androidx-viewmodel:$version"
        const val scope = "org.koin:koin-androidx-scope:$version"
    }

    object Moshi {
        private const val version = "1.9.2"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    object OkHttp {
        private const val version = "4.7.2"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object Roomigrant {
        private const val version = "0.1.7"
        const val library = "com.github.MatrixDev.Roomigrant:RoomigrantLib:$version"
        const val compiler = "com.github.MatrixDev.Roomigrant:RoomigrantCompiler:$version"
    }

    object Spek {
        private const val spekVersion = "2.0.10"
        const val dslJvm = "org.spekframework.spek2:spek-dsl-jvm:$spekVersion"
        const val junit = "org.spekframework.spek2:spek-runner-junit5:$spekVersion"
    }

}