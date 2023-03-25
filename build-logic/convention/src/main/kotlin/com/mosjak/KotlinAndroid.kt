package com.mosjak

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = versionCatalog.getVersion("compileSdk").toInt()

        defaultConfig {
            minSdk = versionCatalog.getVersion("minSdk").toInt()
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }

        kotlin {
            jvmToolchain(JavaVersion.VERSION_11.toString().toInt())
        }

        sourceSets.all { sourceSet ->
            sourceSet.java.srcDir("src/${sourceSet.name}/kotlin")
        }
    }
}

private fun Project.kotlin(block: KotlinAndroidProjectExtension.() -> Unit) {
    block(extensions.getByType())
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
