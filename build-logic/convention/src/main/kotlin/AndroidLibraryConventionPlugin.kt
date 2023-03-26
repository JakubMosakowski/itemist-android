import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.mosjak.configure
import com.mosjak.configureBuildTypes
import com.mosjak.configureJacoco
import com.mosjak.configureKotlinAndroid
import com.mosjak.getByType
import com.mosjak.getLib
import com.mosjak.getVersion
import com.mosjak.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
            apply("io.gitlab.arturbosch.detekt")
            apply("jacoco")
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            configureBuildTypes(this)
            defaultConfig.targetSdk = target.versionCatalog.getVersion("targetSdk").toInt()
        }

        with(dependencies) {
            add("detektPlugins", target.versionCatalog.getLib("detekt"))
        }

        val extension = extensions.getByType<LibraryAndroidComponentsExtension>()
        configureJacoco(extension)
    }
}

