plugins {
    id("com.android.library") version "8.1.4"
    id("maven-publish")
    id("org.jreleaser") version "1.17.0"
}

var varGroupID = "io.github.generaloss"
var varArtifactID = "freetype-jni-natives-android"
var varVersion = "25.7.1-SNAPSHOT-4"

var varDescription = "FreeType Library JNI Android native libraries"
var stagingDir = layout.buildDirectory.dir("staging-deploy").get()

var secretDir = "/home/user/.gnupg"

version = varVersion
group = varGroupID

android {
    namespace = "generaloss.freetype"
    compileSdk = 21

    defaultConfig {
        minSdk = 21
    }

    sourceSets["main"].jniLibs.srcDirs("src/main/jniLibs")

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])

                // add <packaging>aar<packaging> providing plugin
                pom.withXml {
                    val root = asNode()

                    val buildNode = root.appendNode("build")
                    val pluginsNode = buildNode.appendNode("plugins")
                    val pluginNode = pluginsNode.appendNode("plugin")

                    pluginNode.appendNode("groupId", "com.simpligility.maven.plugins")
                    pluginNode.appendNode("artifactId", "android-maven-plugin")
                    pluginNode.appendNode("version", "4.6.0")
                    pluginNode.appendNode("extensions", "true")
                }
            }
            groupId = varGroupID
            artifactId = varArtifactID
            version = varVersion
            pom {
                name = varArtifactID
                description = varDescription
                url = "https://github.com/generaloss/freetype-jni"
                inceptionYear = "2025"
                licenses {
                    license {
                        name = "GNU GPL-v2.0 license"
                        url = "https://www.gnu.org/licenses/old-licenses/gpl-2.0.html"
                    }
                }
                developers {
                    developer {
                        id = "generaloss"
                        name = "Pavel Vladimirovich"
                        email = "generaloss@proton.me"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/generaloss/freetype-jni.git"
                    developerConnection = "scm:git:ssh://github.com:generaloss/freetype-jni.git"
                    url = "https://github.com/generaloss/freetype-jni/tree/master/natives/$varArtifactID"
                }
            }
        }
    }
    repositories {
        maven {
            name = "staging"
            url = stagingDir.asFile.toURI()
        }
    }
}

jreleaser {
    project {
        gitRootSearch.set(true)
        name.set(varArtifactID)
        description.set(varDescription)
        authors.set(listOf("generaloss"))
        license.set("MIT")
        copyright.set("Copyright (c) 2025")
        links {
            homepage.set("https://github.com/generaloss/freetype-jni")
            documentation.set("https://github.com/generaloss/freetype-jni#readme")
        }
    }
    checksum {
        algorithm("MD5")
        algorithm("SHA-1")
        algorithm("SHA-256")
        algorithm("SHA-512")
    }
    files {
        active.set(org.jreleaser.model.Active.ALWAYS)

        var artifactDir = stagingDir.dir("io/github/generaloss/$varArtifactID/$varVersion")

        artifact {
            path.set(artifactDir.file("$varArtifactID-$varVersion.aar"))
        }
        artifact {
            path.set(artifactDir.file("$varArtifactID-$varVersion-sources.jar"))
        }
        artifact {
            path.set(artifactDir.file("$varArtifactID-$varVersion-javadoc.jar"))
        }
        artifact {
            path.set(artifactDir.file("$varArtifactID-$varVersion.pom"))
        }
    }
    signing {
        active.set(org.jreleaser.model.Active.ALWAYS)
        armored = true
        publicKey.set(file("$secretDir/public.asc").readText())
        secretKey.set(file("$secretDir/private.asc").readText())
        passphrase.set(property("signing.passphrase") as String)
    }
    deploy {
        maven {
            mavenCentral.create("sonatype") {
                active.set(org.jreleaser.model.Active.ALWAYS)
                url.set("https://central.sonatype.com/api/v1/publisher")
                stagingRepository(stagingDir.asFile.absolutePath)
                retryDelay.set(60)
                setAuthorization("Basic")
                username.set(property("deploy.username") as String)
                password.set(property("deploy.password") as String)
                deploymentId.set("$varArtifactID:$varVersion")
            }
        }
    }
    release {
        github {
            token.set(property("github.token") as String)
        }
    }
}
