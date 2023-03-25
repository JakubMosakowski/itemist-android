import com.android.build.api.dsl.ApplicationExtension
import com.mosjak.configure
import com.mosjak.configureKotlinAndroid
import com.mosjak.getVersion
import com.mosjak.getVersionCatalog
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = target.getVersionCatalog().getVersion("targetSdk").toInt()
        }
        target.extensions.add("generateVersionCode", ::generateVersionCode)
    }
}

fun generateVersionCode(versionName: String): Int {
    val (major, minor, patch) = versionName.split("-").first().split(".").map { it.toInt() }

    if (major > 99) {
        throw GradleException("Major version of application $major can not be higher than 99")
    }

    if (minor > 99) {
        throw GradleException("Minor version of application $minor can not be higher than 99")
    }

    if (patch > 9) {
        throw GradleException("Patch (hotfix) version $patch can not be higher than 9")
    }

    return major * 1000 + minor * 10 + patch
}

