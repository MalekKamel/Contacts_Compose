package app.common.core.location

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.FragmentActivity
import app.common.core.app.CoreApp
import app.common.core.request_result.permission.PermissionRequester
import app.common.core.util.CrashlyticsLogger
import com.google.android.gms.common.api.Api
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*


class CurrentLocationRetriever(private val callback: (Location) -> Unit) {

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var locationCallback: LocationCallback? = null
    private lateinit var gpsLocationRequester: GpsLocationRequester

    fun retrieve(activity: FragmentActivity) {
        this.gpsLocationRequester = GpsLocationRequester(activity)

        PermissionRequester(activity)
            .request(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) {
                retrieve(callback)
            }
    }

    @SuppressLint("MissingPermission")
    fun retrieve(callback: (Location) -> Unit) {
        GoogleApiClientConnector.connect(listOf(LocationServices.API)) {

            fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(CoreApp.context)
            fusedLocationProviderClient
                ?.lastLocation
                ?.addOnSuccessListener { location ->
                    // Got last known location. In some rare situations, this can be null.
                    if (location != null) {
                        callback(location)
                        return@addOnSuccessListener
                    }
                    requestLocationUpdate(callback)
                }
                ?.addOnFailureListener {
                    Log.d("exception:", it.toString())
                    it.printStackTrace()
                }
        }

    }

    @SuppressLint("MissingPermission", "CheckResult")
    private fun requestLocationUpdate(callback: (Location) -> Unit) {
        try {
            val quality = UpdateQuality()

            val locationRequest = LocationRequest.create().apply {
                interval = quality.interval
                fastestInterval = quality.fastestUpdateInterval
                priority = quality.priority
            }

            //Request Gps
            gpsLocationRequester.request(locationRequest)

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    callback(locationResult.lastLocation)
                    removeLocationUpdates()
                }
            }

            fusedLocationProviderClient?.requestLocationUpdates(
                locationRequest,
                locationCallback!!,
                Looper.getMainLooper()
            )

        } catch (e: Exception) {
            e.printStackTrace()
            CrashlyticsLogger.logAndPrint(e)
        }
    }

    private fun removeLocationUpdates() {
        if (fusedLocationProviderClient != null && locationCallback != null)
            fusedLocationProviderClient!!.removeLocationUpdates(locationCallback!!)
    }

}

object GoogleApiClientConnector {
    private var client: GoogleApiClient? = null

    // TODO: fix deprecation
    fun connect(
        apis: List<Api<out Api.ApiOptions.NotRequiredOptions>>,
        connectedCallback: (GoogleApiClient?) -> Unit
    ): GoogleApiClient {

        val connectionCallback = object : GoogleApiClient.ConnectionCallbacks {
            override fun onConnected(bundle: Bundle?) {
                connectedCallback(client)
            }

            override fun onConnectionSuspended(i: Int) {}
        }

        // TODO: fix deprecation
        val builder = GoogleApiClient.Builder(CoreApp.context)
            .addConnectionCallbacks(connectionCallback)
            .addOnConnectionFailedListener { connectionResult ->
                Log.e(
                    "GoogleApiClientUtil",
                    "connectionResult = " + connectionResult.errorMessage!!
                )
                CrashlyticsLogger.logAndPrint(connectionResult.errorMessage)
            }

        for (api in apis) builder.addApi(api)

        client = builder.build()
        client?.connect()
        return client!!
    }

}

class UpdateQuality {
    var interval: Long = 0
        private set
    var fastestUpdateInterval = 2.toLong() * 1000
        private set
    var priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        private set

    /**
     * Set interval of LocationRequest
     * default: 0
     * @param interval in milliseconds
     * @return thi
     */
    fun interval(interval: Long): UpdateQuality {
        this.interval = interval
        return this
    }

    /**
     * Set fastest update interval of LocationRequest
     * default: 2 * 1000
     * @param interval in milliseconds
     * @return thi
     */
    fun fastestUpdateInterval(interval: Long): UpdateQuality {
        fastestUpdateInterval = interval
        return this
    }

    /**
     * Set priority of LocationRequest
     * default: LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
     * @param priority for example: `LocationRequest.PRIORITY_LOW_POWER `
     * @return thi
     */
    fun priority(priority: Int): UpdateQuality {
        this.priority = priority
        return this
    }
}
