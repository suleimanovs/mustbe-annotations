rootProject.name = "mustBe"
include("mustbe-annotations")
include("mustbe-processor")
include("mustbe-sample")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenLocal() 
        mavenCentral()
    }
}

dependencyResolutionManagement {
    versionCatalogs {

        create("pomData") {
            from(files("gradle/pom.versions.toml"))
        }
    }
    repositories {
        google()
        mavenLocal() 
        mavenCentral()
    }
} 