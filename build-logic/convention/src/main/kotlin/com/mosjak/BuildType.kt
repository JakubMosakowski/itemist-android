package com.mosjak

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        buildTypes {
            getByName("debug").let {
                it.isJniDebuggable = true
            }
            create("qa").let {
                it.initWith(getByName("debug"))

                it.isMinifyEnabled = true
                it.proguardFiles("proguard-rules.pro")

                it.matchingFallbacks.add("release")
            }
            getByName("release").let {
                it.isJniDebuggable = false
                it.isMinifyEnabled = true
                it.proguardFiles("proguard-rules.pro")
            }
        }
    }
}
