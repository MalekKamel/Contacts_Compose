package app.common.core.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import app.common.core.request_result.permission.PermissionRequester

class GPSTracker(val callBack: (Location) -> Unit) : LocationListener {

    private var mLocationManager: LocationManager? = null

    var location: Location? = null

    fun retrieve(activity: FragmentActivity) {

        PermissionRequester(activity)
            .request(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) {
                setup(activity)
                getCurrentLocation()
            }
    }

    fun setup(activity: FragmentActivity) {
        GPsPermission(activity).requireGPS()
        mLocationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {

        val isGPSEnabled: Boolean =
            mLocationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled: Boolean =
            mLocationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        location = null

        when {
            isNetworkEnabled -> {
                //if (PermissionHelper(context).isLocationPermitted())
                mLocationManager!!.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    LOCATION_UPDATE_MIN_TIME.toLong(),
                    LOCATION_UPDATE_MIN_DISTANCE.toFloat(),
                    this
                )
                location = mLocationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            }
            isGPSEnabled -> {
                mLocationManager!!.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    LOCATION_UPDATE_MIN_TIME.toLong(), LOCATION_UPDATE_MIN_DISTANCE.toFloat(), this
                )
                location = mLocationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            }
            else -> Log.d("getCurrentLocation", "GPS NOT Enabled")
        }

        location?.let {
            invokeCallBack(it)
        }
    }

    fun invokeCallBack(location: Location) {
        callBack(location)
        stopUsingGPS()
    }

    @SuppressLint("MissingPermission")
    fun stopUsingGPS() {
        mLocationManager?.let {
            it.removeUpdates(this)
        }
    }

    override fun onLocationChanged(location: Location) {
        invokeCallBack(location)
    }

    override fun onStatusChanged(s: String, i: Int, bundle: Bundle) {}
    override fun onProviderEnabled(s: String) {}
    override fun onProviderDisabled(s: String) {}
}

const val LOCATION_UPDATE_MIN_DISTANCE = 10
const val LOCATION_UPDATE_MIN_TIME = 2000