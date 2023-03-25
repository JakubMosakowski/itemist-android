import com.mosjak.getLib
import com.mosjak.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply("de.mannodermaus.android-junit5")
        }

        with(dependencies) {
            add("testImplementation", versionCatalog.getLib("jupiter-api"))
            add("testRuntimeOnly", versionCatalog.getLib("jupiter-engine"))
            add("testImplementation", versionCatalog.getLib("mockito-core"))
            add("testImplementation", versionCatalog.getLib("mockito-kotlin"))
        }
    }
}
