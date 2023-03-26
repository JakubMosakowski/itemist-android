import com.android.build.gradle.LibraryExtension
import com.mosjak.configureAndroidCompose
import com.mosjak.getByType
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        pluginManager.apply("com.android.library")
        val extension = extensions.getByType<LibraryExtension>()
        configureAndroidCompose(extension)
    }
}
