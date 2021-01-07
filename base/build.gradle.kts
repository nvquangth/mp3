plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinAndroidExt)
    id(GradlePlugins.kotlinKapt)
}

android {
    compileSdkVersion(Android.compileSdk)

    defaultConfig {
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)

        versionCode = Android.versionCode

        testInstrumentationRunner = AndroidJUnit.testInstrumentationRunner

        multiDexEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
    }

    androidExtensions {
        isExperimental = true
    }
}

dependencies {

    // Module
    implementation(project(Modules.entity))

    implementation(Libs.stdLib)

    // Activity
    implementation(Libs.activity)

    // Fragment
    implementation(Libs.fragment)

    // ConstraintLayout
    implementation(Libs.constraintLayout)

    // Appcompat
    implementation(Libs.appcompat)

    // Android Core
    implementation(Libs.coreKtx)

    // ViewModel + LiveData Lifecycle
    implementation(Libs.viewModel)
    implementation(Libs.liveData)
    implementation(Libs.lifecycleProcessorJava8)
    kapt(Libs.lifecycleProcessor)

    // Multidex
    implementation(Libs.multidex)

    // Navigation
    api(Libs.navigationFragment)
    api(Libs.navigationUi)

    // RecyclerView
    implementation(Libs.recyclerView)

    // Room
    implementation(Libs.room)
    implementation(Libs.roomExt)
    kapt(Libs.roomProcessor)

//    // ViewPager2
//    implementation(Libs.viewPager2)

    // Retrofit
    implementation(Libs.retrofit)
    implementation(Libs.retrofitGson)

    // OkHttp
    implementation(Libs.okHttp)
    implementation(Libs.okHttpLogging)
    testImplementation(Libs.okHttpMockServer)

    // Glide
    implementation(Libs.glide)
    kapt(Libs.glideProcessor)

    // Hilt
    implementation(Libs.hiltDagger)
    kapt(Libs.hiltDaggerCompiler)
    implementation(Libs.hiltViewModel)
    kapt(Libs.hiltAndroidProcessor)

    // JUnit
    testImplementation(Libs.jUnit)
    androidTestImplementation(Libs.jUnitExt)
    androidTestImplementation(Libs.espresso)

    // Mockito
    implementation(Libs.mockito)
    testImplementation(Libs.coroutinesTest)
    testImplementation(Libs.archTest)

    // Material Design
    implementation(Libs.material)

    // CardView
    implementation(Libs.cardView)

    // Preference
    implementation(Libs.preference)
}