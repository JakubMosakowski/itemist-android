package com.mosjak

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) = with(commonExtension) {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = versionCatalog.getVersion("composeCompiler")
    }

    with(dependencies) {
        val bom = versionCatalog.getLib("compose-bom")
        add("implementation", platform(bom))
    }
}
