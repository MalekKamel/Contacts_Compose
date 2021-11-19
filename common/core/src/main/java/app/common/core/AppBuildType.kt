package app.common.core

enum class AppBuildType(val value: String) {
    Release("release"),
    Debug("debug"),
    Stage("staging"),
    Internal("internal");

    companion object {
        fun testMode(): Boolean {
            return BuildConfig.BUILD_TYPE != Release.value
        }
    }
}

