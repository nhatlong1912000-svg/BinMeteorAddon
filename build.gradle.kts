plugins {
    alias(libs.plugins.fabric.loom)
}

base {
    archivesName = providers.gradleProperty("archives_base_name").get()
    version = libs.versions.mod.version.get()
    group = providers.gradleProperty("maven_group").get()
}

repositories {
    maven { name = "meteor-releases"; url = uri("https://maven.meteordev.org/releases") }
    maven { name = "meteor-snapshots"; url = uri("https://maven.meteordev.org/snapshots") }
}

dependencies {
    minecraft(libs.minecraft)
    mappings(variantOf(libs.yarn) { classifier("v2") })
    modImplementation(libs.fabric.loader)
    modImplementation(libs.meteor.client)
}

tasks {
    processResources {
        val props = mapOf("version" to project.version, "mc_version" to libs.versions.minecraft.get())
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("fabric.mod.json") { expand(props) }
    }
    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release = 21
    }
}
