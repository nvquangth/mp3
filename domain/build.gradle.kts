plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
}

apply {
    from("../ktlint.gradle.kts")
}

dependencies {

    // Module
    implementation(project(Modules.entity))

    implementation(Libs.stdLib)

    // Java inject
    implementation(Libs.javaInject)

    // JUnit
    testImplementation(Libs.jUnit)

    // Mockito
    implementation(Libs.mockito)
    testImplementation(Libs.coroutinesTest)
}
