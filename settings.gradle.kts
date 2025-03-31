pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Demo"
include(":app")
include(":common:di")
include(":common:ui")
include(":common:navigation:api")
include(":common:navigation:impl")
include(":common:navigation:wiring")
include(":feature:payment:presentation")
include(":feature:payment:domain")
include(":feature:payment:data")
include(":feature:payment:wiring")
