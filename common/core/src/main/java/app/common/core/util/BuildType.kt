package app.common.core.util

import app.common.core.BuildConfig

enum class BuildType(val value: String) {
    Debug("debug"),
    Staging("staging"),
    Internal("internal"),
    Release("release");

    companion object {
        val current: BuildType
            get() {
                return values().first {
                    it.value == BuildConfig.BUILD_TYPE
                }
            }
    }
}