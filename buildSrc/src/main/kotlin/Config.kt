import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 21
    const val compileSdk = 31
    const val targetSdk = 31
    val javaVersion = JavaVersion.VERSION_1_8
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val baseUrlProd = "https://www.google.com/"
    const val baseUrlDev = "https://www.google.com/"
}
