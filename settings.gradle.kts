dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://gitlab.com/api/v4/projects/42098339/packages/maven")
    }
}
rootProject.name = "Jiver"
include(":app")
include(":data")
include(":domain")
include(":meta")
include(":ui")
include(":test")
include(":vendor")
