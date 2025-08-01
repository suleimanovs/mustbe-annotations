plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":mustbe-annotations"))
    ksp(project(":mustbe-processor"))
}