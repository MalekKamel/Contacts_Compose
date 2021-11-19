object Versions {
    const val appCompat = "1.3.1"
    const val core = "1.6.0"
    const val recyclerView = "1.0.0"
    const val navigation = "2.3.5"

    const val materialDesign = "1.4.0"
    const val picasso = "2.71828"
    const val constraintLayout = "2.0.0-alpha2"
    const val retrofit = "2.6.1"
    const val okHttp = "3.10.0"

    const val lifeCycle = "2.0.0-rc01"

    const val formValidator = "2.1.0"
    const val navigator = "1.7.0"
    const val modelMapper = "0.1.0"
    const val coreKtx = "1.6.0"
    const val paging = "2.0.0-rc01"
    const val swiperefreshlayout = "1.0.0"
    const val bulletin = "0.1.3"
    const val coroutineRequester = "0.5.0"
    const val ViewModelExt = "2.3.1"

    const val room = "2.4.0-alpha04"
    const val preference = "1.1.1"

    const val workManager = "2.7.0-alpha04"
    const val concurrentFutures = "1.1.0"

    const val pinView = "v1.4"
    const val ccp = "2.4.4"

    //Google Maps
    const val gmsLocation = "18.0.0"
    const val gmsKtx = "2.2.0"
    const val places = "2.4.0"

    //Coroutines
    const val coroutinesCore = "1.5.0"
}

object Libs {
    val koin = KoinLibs
    val compose = ComposeLibs
    val androidX = AndroidXLibs
    val socketIoLibs = SocketIoLibs
    val sha = ShaLibs
    val retrofit = RetrofitLibs
    val square = SquareLibs
    val materialDesign = MaterialDesignLibs
    val textFields = TextFieldLibs
    val jakeWharton = JakeWhartonLibs
    val phoneValidation = PhoneValidation
    val maps = GoogleMaps
    val coroutines = CoroutinesLibs
    val adjust = AdjustLibs

    object KoinLibs {
        private const val version = "3.1.0"
        const val android = "io.insert-koin:koin-android:$version"
        const val java = "org.koin:koin-java:$version"
        const val workManager = "io.insert-koin:koin-androidx-workmanager:$version"
    }

    object AndroidXLibs {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val core = "androidx.core:core:${Versions.core}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val navigation_fragment_ktx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigation_ui_ktx =
            "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val lifeCycle =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycle}"
        const val lifeCycleCommonJava8 =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
        const val swiperefreshlayout =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
        const val ViewModelExt =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ViewModelExt}"
        const val preference =
            "androidx.preference:preference:${Versions.preference}"

        const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
        const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
        const val room_ktx = "androidx.room:room-ktx:${Versions.room}"

        const val workManager = "androidx.work:work-runtime:${Versions.workManager}"
        const val concurrentFutures =
            "androidx.concurrent:concurrent-futures:${Versions.concurrentFutures}"
    }

    object ComposeLibs {
        const val version = "1.0.1"
        const val constraintComposeVersion = "1.0.0-beta02"

        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val layout = "androidx.compose.foundation:foundation-layout:$version"
        const val material = "androidx.compose.material:material:$version"
        const val materialIconsExtended =
            "androidx.compose.material:material-icons-extended:$version"
        const val runtime = "androidx.compose.runtime:runtime:$version"
        const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val test = "androidx.compose.test:test-core:$version"
        const val uiTest = "androidx.compose.ui:ui-test:$version"
        const val constraintCompose =
            "androidx.constraintlayout:constraintlayout-compose:$constraintComposeVersion"

        const val coilVersion = "1.3.1"
        const val coil = "io.coil-kt:coil-compose:$coilVersion"
    }

    object ShaLibs {
        const val formValidator =
            "com.github.ShabanKamell:FormValidator:${Versions.formValidator}"
        const val formValidator_core =
            "com.github.ShabanKamell.FormValidator:core:${Versions.formValidator}"
        const val navigator = "com.github.ShabanKamell:Navigator:${Versions.navigator}"
        const val modelMapper =
            "com.github.ShabanKamell:ModelMapper:${Versions.modelMapper}"
        const val bulletin = "com.github.ShabanKamell:Bulletin:${Versions.bulletin}"
        const val coroutineRequester =
            "com.github.ShabanKamell:CoroutineRequester:${Versions.coroutineRequester}"
    }

    object RetrofitLibs {
        const val runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object MaterialDesignLibs {
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    }

    object SquareLibs {
        const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
        const val okHttp_runtime = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val okHttp_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object TextFieldLibs {
        const val pinView = "com.github.GoodieBag:Pinview:${Versions.pinView}"
        const val ccp = "com.hbb20:ccp:${Versions.ccp}"
    }

    object JakeWhartonLibs {
        private const val threeTenVersion = "1.2.2"

        const val threeTen = "com.jakewharton.threetenabp:threetenabp:${threeTenVersion}"
    }

    object PhoneValidation {
        const val version = "8.12.13"

        const val libPhoneNumber = "io.michaelrocks:libphonenumber-android:${version}"
    }

    object GoogleMaps {
        const val gmsLocation =
            "com.google.android.gms:play-services-location:${Versions.gmsLocation}"
        const val mapsKtx = "com.google.maps.android:maps-ktx:${Versions.gmsKtx}"
        const val places = "com.google.android.libraries.places:places:${Versions.places}"
    }

    object CoroutinesLibs {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    }

    object SocketIoLibs {
        private const val version = "1.0.1"
        const val socket = "io.socket:socket.io-client:$version"
    }

    object AdjustLibs {
        private const val version = "4.28.2"
        const val adjust_android = "com.adjust.sdk:adjust-android:$version"
        const val adjust_web_bridge = "com.adjust.sdk:adjust-android-webbridge:$version"
        const val install_referrer = "com.android.installreferrer:installreferrer:2.2"
    }
}

object TestLibs {
    object Versions {
        const val test_junit = "4.12"
        const val test_jUnitParams = "1.1.1"
        const val test_kotlinTestJunit5 = "3.4.0"
        const val test_kotlinTestExtKoin = "3.4.0"
        const val test_espressoCore = "3.1.0-alpha4"
        const val junit = "1.1.1"
        const val espressoCore = "3.2.0"
        const val kotlinx_coroutine_test = "1.5.0"
        const val core_testing = "2.1.0"
        const val mockito_core = "3.0.0"
        const val mockito_kotlin = "2.2.0"
        const val koin = "3.1.0"
    }

    val androidX = AndroidXLibs

    object AndroidXLibs {
        const val espressoCore =
            "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
        const val junit = "androidx.test.ext:junit:${Versions.junit}"

        const val kotlinx_coroutine_test =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinx_coroutine_test}"

        const val core_testing =
            "androidx.arch.core:core-testing:${Versions.core_testing}"
    }

    const val junit = "junit:junit:${Versions.test_junit}"
    const val jUnitParams = "pl.pragmatists:JUnitParams:${Versions.test_jUnitParams}"

    const val kotlinTestJunit5 =
        "io.kotlintest:kotlintest-runner-junit5:${Versions.test_kotlinTestJunit5}"
    const val kotlinTestExtKoin =
        "io.kotlintest:kotlintest-extensions-koin:${Versions.test_kotlinTestExtKoin}"

    const val espressoCore =
        "androidx.test.espresso:espresso-core:${Versions.test_espressoCore}"
    const val koin_test = "io.insert-koin:koin-test:${Versions.koin}"


    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    const val mockito_kotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"
}