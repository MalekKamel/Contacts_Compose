package app.common.presentation.mvvm.route

sealed class RegisterRoute : RouteInterface {
    object Reset: RegisterRoute()
}
