package com.mosjak

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension

fun Project.getVersionCatalog(): VersionCatalog =
    extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.getVersion(name: String): String =
    findVersion(name).get().toString()
