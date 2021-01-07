object Android {
    const val applicationId = "com.bt.mp3"
    const val compileSdk    = 29
    const val buildTools    = "29.0.3"
    const val minSdk        = 21
    const val targetSdk     = 29
    const val versionCode   = 100
    const val versionNam    = "1.0.0"
}

object AndroidJUnit {
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildType {
    const val debug         = "debug"
    const val minifyDebug   = false
    const val proguardDebug = "proguard-rule.pro"

    const val release           = "release"
    const val minifyRelease     = false
    const val proguardRelease   = "proguard-release.pro"
}

object ProductFlavor {
    const val qa                = "qa"
    const val applicationIdQa   = "com.bt.mp3.qa"
    const val versionCodeQa     = 201
    const val versionNameQa     = "2.0.1.QA"
    const val baseUrlQa         = "\"https://api.themoviedb.org\""

    const val develop               = "dev"
    const val applicationIdDevelop  = "com.bt.mp3.dev"
    const val versionCodeDevelop    = 201
    const val versionNameDevelop    = "2.0.1.DEV"
    const val baseUrlDevelop        = "\"https://api.themoviedb.org\""

    const val staging               = "stg"
    const val applicationIdStaging  = "com.bt.mp3.stg"
    const val versionCodeStaging    = 115
    const val versionNameStaging    = "1.1.5.STG"
    const val baseUrlStaging        = "\"https://api.themoviedb.org\""

    const val production                = "prod"
    const val applicationIdProduction   = "com.bt.mp3"
    const val versionCodeProduction     = 100
    const val versionNameProduct        = "1.0.0"
    const val baseUrlProduction         = "\"https://api.themoviedb.org\""

    const val baseUrlParam = "BASE_URL"
}
