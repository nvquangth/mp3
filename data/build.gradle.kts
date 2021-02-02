import java.io.FileInputStream
import java.util.Properties

plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinAndroidExt)
    id(GradlePlugins.kotlinKapt)
    id(GradlePlugins.hiltAndroid)
    jacoco
}

apply {
    from("../ktlint.gradle.kts")
}

jacoco {
    toolVersion = "0.8.5"
}

/** There are two ways to see test result:
 * FIRST
 * To run this test coverage with buildTypes: Debug; Flavor: Dev
 * use command: ./gradlew clean testDevDebugUnitTestCoverage_Data
 *
 * See result at:
 * app/build/reports/jacoco/testDevDebugUnitTestCoverage_Data/html/index.html
 *
 * SECOND:
 *  - Click Gradle on the right menu of Android Studio IDE
 *  - At Project name, expand "app", expand "Tasks", expand "coverage"
 *  - Run any test you want
 */
project.afterEvaluate {
    // Grab all build types and product flavors
    val buildTypeNames: List<String> = android.buildTypes.map { it.name }
    val productFlavorNames: ArrayList<String> = ArrayList(android.productFlavors.map { it.name })
    // When no product flavors defined, use empty
    if (productFlavorNames.isEmpty()) productFlavorNames.add("")
    productFlavorNames.forEach { productFlavorName ->
        buildTypeNames.forEach { buildTypeName ->
            val sourceName: String
            val sourcePath: String
            if (productFlavorName.isEmpty()) {
                sourcePath = buildTypeName
                sourceName = buildTypeName
            } else {
                sourcePath = "${productFlavorName}/${buildTypeName}"
                sourceName = "${productFlavorName}${buildTypeName.capitalize()}"
            }
            val testTaskName = "test${sourceName.capitalize()}UnitTest"
            // Create coverage task of form 'testFlavorTypeCoverage' depending on 'testFlavorTypeUnitTest'
            task<JacocoReport>("${testTaskName}Coverage_Data") {
                //where store all test to run follow second way above
                group = "coverage"
                description =
                    "Generate Jacoco coverage reports on the ${sourceName.capitalize()} build."
                val excludeFiles = arrayListOf(
                    "**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*",
                    "**/*Test*.*", "android/**/*.*",
                    "**/*_MembersInjector.class",
                    "**/Dagger*Component.class",
                    "**/Dagger*Component\$Builder.class",
                    "**/com/bt/mp3/data/di/**/*.*",
                    "**/com/bt/mp3/data/extension/**/*.*",
                    "**/com/bt/mp3/data/annotation/**/*.*",
                    "**/com/bt/mp3/data/interceptor/**/*.*",
                    "**/com/bt/mp3/data/remote/**/*.*",
                    "**/com/bt/mp3/data/local/**/*.*"
                )

                //Explain to Jacoco where are you .class file java and kotlin
                classDirectories.setFrom(
                    fileTree("${project.buildDir}/intermediates/classes/${sourcePath}").exclude(
                        excludeFiles
                    ),
                    fileTree("${project.buildDir}/tmp/kotlin-classes/${sourceName}").exclude(
                        excludeFiles
                    )
                )
                val coverageSourceDirs = arrayListOf(
                    "src/main/java",
                    "src/$productFlavorName/java",
                    "src/$buildTypeName/java"
                )

                additionalSourceDirs.setFrom(files(coverageSourceDirs))

                //Explain to Jacoco where is your source code
                sourceDirectories.setFrom(files(coverageSourceDirs))

                //execute file .exec to generate data report
                executionData.setFrom(files("${project.buildDir}/jacoco/${testTaskName}.exec"))

                reports {
                    xml.isEnabled = true
                    html.isEnabled = true
                }
                dependsOn(testTaskName)
            }
        }
    }
}

android {
    compileSdkVersion(Android.targetSdk)

    defaultConfig {
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)

        testInstrumentationRunner = AndroidJUnit.testInstrumentationRunner
    }

    buildTypes {
        getByName(BuildType.debug) {
            buildConfigField(
                "String",
                "BASE_URL",
                getConfig()["BASE_URL"] as String
            )

            buildConfigField(
                "String",
                "API_KEY",
                getConfig()["API_KEY"] as String
            )

            buildConfigField(
                "String",
                "BASE_URL_AUTH",
                getConfig()["BASE_URL_AUTH"] as String
            )
        }

        getByName(BuildType.release) {
            buildConfigField(
                "String",
                "BASE_URL",
                getConfig()["BASE_URL"] as String
            )

            buildConfigField(
                "String",
                "API_KEY",
                getConfig()["API_KEY"] as String
            )

            buildConfigField(
                "String",
                "BASE_URL_AUTH",
                getConfig()["BASE_URL_AUTH"] as String
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}

fun getConfig(): Properties = Properties().apply {
    load(FileInputStream(File("config.properties")))
}

dependencies {

    // Module
    implementation(project(Modules.entity))
    implementation(project(Modules.domain))

    implementation(Libs.stdLib)

    // Android Core
    implementation(Libs.coreKtx)

    // Hilt
    implementation(Libs.hiltDagger)
    kapt(Libs.hiltDaggerCompiler)

    // Room
    implementation(Libs.room)
    implementation(Libs.roomExt)
    kapt(Libs.roomProcessor)

    // Retrofit
    implementation(Libs.retrofit)
    implementation(Libs.retrofitGson)

    // OkHttp
    implementation(Libs.okHttp)
    implementation(Libs.okHttpLogging)
    testImplementation(Libs.okHttpMockServer)

    // JUnit
    testImplementation(Libs.jUnit)
    androidTestImplementation(Libs.jUnitExt)
    androidTestImplementation(Libs.espresso)

    // Mockito
    implementation(Libs.mockito)
    testImplementation(Libs.coroutinesTest)

    // Robolectric
    testImplementation(Libs.robolectric)

    // Volley
    implementation("com.android.volley:volley:1.1.1")
}