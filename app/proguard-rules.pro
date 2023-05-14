
-flattenpackagehierarchy ''
-allowaccessmodification

-keepattributes Signature
-optimizationpasses 5
-dontusemixedcaseclassnames

-verbose
-printseeds seeds.txt
-printusage unused.txt
-printmapping mapping.txt
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-allowaccessmodification
-keepattributes *Annotation*
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

-keepattributes Signature
-keepattributes Exceptions

-ignorewarnings

# Architecture Components
-keep class androidx.core.** { *; }
-keep class androidx.room.** { *; }
-keep class com.google.android.material.** { *; }
-keep class androidx.compose.material.** { *; }
-keep class androidx.test.** { *; }
-keep class androidx.lifecycle.** { *; }
-keep class androidx.datastore.** { *; }

# Jetpack Compose
-keep class androidx.activity.** { *; }
-keep class androidx.compose.ui.** { *; }
-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.material3.** { *; }
-keep class androidx.constraintlayout.** { *; }
-keep class androidx.navigation.** { *; }

# Retrofit
-keep class retrofit2.** { *; }
-keep class com.squareup.retrofit2.** { *; }

# OkHttp
-keep class okhttp3.** { *; }
-keep class com.squareup.okhttp3.** { *; }

# Chuck Interceptor
-keep class com.github.chuckerteam.chucker.** { *; }

# Moshi
-keep class com.squareup.moshi.** { *; }

# Keep class names of Hilt injected ViewModels since their name are used as a multibinding map key.
-keepnames @dagger.hilt.android.lifecycle.HiltViewModel class * extends androidx.lifecycle.ViewModel

# Keep Hilt generated code
-keep class **$$Hilt*
-keep class **$$ViewInjector

# Keep Hilt generated members
-keepclassmembers class * {
    @dagger.hilt.android.internal.GeneratedAccessLevel *;
}

# Keep Hilt generated constructors
-keepclassmembers class * {
    @dagger.hilt.android.internal.Factory <init>(...);
}

# Keep Hilt generated field initializers
-keepclassmembers class * {
    void set*(***);
}

# Coil
-keep class io.coil-kt.** { *; }

# Pager
-keep class com.google.accompanist.** { *; }

# Test Implementation
-keep class junit.** { *; }
-keep class androidx.test.** { *; }
-keep class android.test.** { *; }
-keep class io.mockk.** { *; }
-keep class org.jetbrains.kotlinx.** { *; }

# SLF4J
-keep class org.slf4j.** { *; }


-keep class org.bouncycastle.jsse.BCSSLParameters { *; }
-keep class org.bouncycastle.jsse.BCSSLSocket { *; }
-keep class org.bouncycastle.jsse.provider.BouncyCastleJsseProvider { *; }
-keep class org.conscrypt.Conscrypt$Version { *; }
-keep class org.conscrypt.Conscrypt { *; }
-keep class org.conscrypt.ConscryptHostnameVerifier { *; }
-keep class org.openjsse.javax.net.ssl.SSLParameters { *; }
-keep class org.openjsse.javax.net.ssl.SSLSocket { *; }
-keep class org.openjsse.net.ssl.OpenJSSE { *; }

-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE

-keep class java.lang.** { *; }

-keep class androidx.compose.foundation.lazy.LazyColumnKt { *; }

-keepclassmembers,allowobfuscation class * {
    *** get*(...);
    *** set*(...);
}

-keepattributes Exceptions