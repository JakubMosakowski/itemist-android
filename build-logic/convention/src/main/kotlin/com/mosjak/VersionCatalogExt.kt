package com.mosjak

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider

val Project.versionCatalog: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.getVersion(name: String): String =
    findVersion(name).get().toString()

fun VersionCatalog.getLib(name: String): Provider<MinimalExternalModuleDependency> =
    findLibrary(name).get()
