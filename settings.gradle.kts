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
        maven(url = "https://devrepo.kakao.com/nexus/repository/kakaomap-releases/")

        // 아래 코드 추가
        maven(url = "https://devrepo.kakao.com/nexus/content/groups/public/")

    }

    rootProject.name = "app_helper_fe"
    include(":app")
}
