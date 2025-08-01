plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.dokka)
    alias(libs.plugins.signing)
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

tasks.register<Jar>("dokkaJar") {
    archiveClassifier.set("javadoc")
    from(tasks.named("dokkaJavadocJar"))
}

mavenPublishing {
    coordinates(
        pomData.versions.group.get(),
        "mustbe-annotations",
        pomData.versions.projectVersion.get()
    )

    pom {
        name.set(pomData.versions.name.get() + " Annotations")
        description.set("Annotations for MustBe KSP validation library.")
        inceptionYear.set(pomData.versions.inceptionYear.get())
        url.set(pomData.versions.url.get())
        licenses {
            license {
                name.set(pomData.versions.licenseName.get())
                url.set(pomData.versions.licenseUrl.get())
                distribution.set(pomData.versions.licenseDist.get())
            }
        }
        developers {
            developer {
                id.set(pomData.versions.developerId.get())
                name.set(pomData.versions.developerName.get())
                url.set(pomData.versions.developerUrl.get())
            }
        }
        scm {
            url.set(pomData.versions.scmUrl.get())
            connection.set(pomData.versions.scmConnection.get())
            developerConnection.set(pomData.versions.scmDevConnection.get())
        }
    }
}

signing {
    file("$rootDir/mustbe_private.asc").readText().takeIf { it.isNotBlank() }?.let {secretKey ->
        useInMemoryPgpKeys(findProperty("signing.keyId") as String?, secretKey, findProperty("signing.password") as String?)
        sign(publishing.publications)
    }
}