dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Jiver"
include (":app")
include (":data")
include (":domain")
include (":meta")
include (":ui")
include (":test")
include (":vendor")
