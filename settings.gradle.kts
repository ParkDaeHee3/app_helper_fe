pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        //카카오api 추가
        maven(url = uri("https://devrepo.kakao.com/nexus/repository/kakaomap-releases/"))
    }
}

rootProject.name = "app_helper_fe"
include(":app")
 